package com.aaa.cloud.base;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: zcl
 * @ClassName: CommonController
 * @Description: 通用Controller
 * * 作用范围：        当前类   同一个包   其他package          同包子孙类           不同包子孙类
 * * public           可以      可以        可以                 可以                 可以
 * * protected        可以      可以        不可以               可以                 可以
 * * default          可以      可以        不可以               可以                 不可以
 * * private          可以      不可以       不可以              不可以               不可以
 * @Date: 2020/7/8 16:37
 */
public abstract class CommonController<T> extends BaseController {


    public abstract BaseService<T> getBaseService();


    /**
     * @Author: zcl
     * @Methodname: beforeAdd
     * @Description: 钩子函数
     * 在新增之前去执行某些操作
     * 下单操作:
     * 需求
     * 在购物车中当点击立即下单按钮的时候--->跳转下单页面(选择地址，选择优惠券...)
     * 把购物车中的这个商品删除
     * deleteCart(List<Integer> id);--->是优先于insertOrder前置执行
     * <p>
     * insertOrder(Order oder);
     * @Date: 2020/7/9 21:04
     * @Param: [map]
     * @Return: void
     */
    protected void beforeAdd(Map map) {
        // TODO AddMethod Before to do something
    }


    /**
     * @throws
     * @Author: zcl
     * @Methodname: afterAdd
     * @Description:
     * 作用范围：        当前类   同一个包   其他package          同包子孙类           不同包子孙类
     * public           可以      可以        可以                 可以                 可以
     * protected        可以      可以        不可以               可以                 可以
     * default          可以      可以        不可以               可以                 不可以
     * private          可以      不可以       不可以              不可以               不可以
     * @author Seven Lee
     * @description 钩子函数
     * 是在新增之后去执行
     * <p>
     * int result = insertOrder(Order order)
     * if(result > 0) {
     * insertOrderDetail(OrderDetail orderDetail);
     * }
     * @Date: 2020/7/9 21:05
     * @Param: [map]
     * @Return: void
     */
    protected void afterAdd(Map map) {
        // TODO AddMethod After to do something
    }

    /**
     * @Author: zcl
     * @Methodname: add
     * @Description: 新增方法
     * 因为咱们目前市面上所有的公司实现的全是“异步”了
     * 也就是说前端向后端所传递的数据都是json格式
     * 之前在controller的方法中接收固定的实体类，是因为你知道前端给你传递的类型就是这个实体类
     * 但是既然要做通用，前端所传递的类型就不会固定了--->所以使用Map类型来统一接收
     * @Date: 2020/7/9 19:23
     * @Param: [map]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData add(@RequestBody Map map) {
        // 因为根据咱们的封装规则，在service中是需要传递泛型的，就意味着service需要接收固定的实体类
        // 但是controller是一个Map类型
        beforeAdd(map);
        // 1.Map<String,Object>转实体类
        T instance = getBaseService().newInstance(map);
        // 2.通用service
        Integer addResult = getBaseService().add(instance);
        if (addResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

        /**
         * @Author: zcl
         * @Methodname: delete
         * @Description:
         *         根据主键/普通列删除
         * @Date: 2020/7/16 13:10
         * @Param: [id]
         * @Return: com.aaa.cloud.base.ResultData
         **/
        public ResultData deleteByPrimaryKey(@RequestParam("id") Object id){
            Integer deleteInteger = getBaseService().deleteByPrimaryKey((T) id);
            if (deleteInteger > 0) {
                return super.operationSuccess();
            }
            return super.operationFailed();
        }

    /**
     * @Author: zcl
     * @Methodname: delete
     * @Description: 根据主键批量删除
     *          value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入；
     *          required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
     *          defaultValue：默认值，表示如果请求中没有同名参数时的默认值，默认值可以是SpEL表达式，如“#{systemProperties['java.vm.version']}”。
     *          @RequestParam(value="username", required=true, defaultValue="zhang") String username)
     * @Date: 2020/7/9 20:09
     * @Param: [ids]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData delete(@RequestParam("ids[]") Integer[] ids) {
        Integer deleteResult = getBaseService().deleteByIds(Arrays.asList(ids));
        if (deleteResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author: zcl
     * @Methodname: batchUpdate
     * @Description: 批量更新
     * @Date: 2020/7/9 20:16
     * @Param: [map, ids]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData batchUpdate(@RequestBody Map map, @RequestParam("ids") Integer[] ids) {
        T t = getBaseService().newInstance(map);
        Integer batchUpdateResult = getBaseService().batchUpdate(t, ids);
        if (batchUpdateResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

    /**
     * @Author: zcl
     * @Methodname: update
     * @Description: 更新操作
     * @Date: 2020/7/9 20:31
     * @Param: [map]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData update(@RequestBody Map map) {
        T t = getBaseService().newInstance(map);
        Integer updateResult = getBaseService().update(t);
        if (updateResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

    /**
     * @Author: zcl
     * @Methodname: select
     * @Description: 查询集合，条件查询
     * @Date: 2020/7/9 20:19
     * @Param: [map]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData select(@RequestBody Map map) {
        T t = getBaseService().newInstance(map);
        List<T> selectResult = getBaseService().select(t);
        if (null != selectResult && selectResult.size()>0) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

    /**
     * @Author: zcl
     * @Methodname: selectOne
     * @Description: 查询一条数据
     * @Date: 2020/7/9 20:31
     * @Param: [map]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData selectOne(@RequestBody Map map) {
        T t = getBaseService().newInstance(map);
        T tOneResult = getBaseService().selectOne(t);
        if (null != tOneResult) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

    /**
     * @Author: zcl
     * @Methodname: selectAll
     * @Description:
     *         查询所有数据
     * @Date: 2020/7/16 11:46
     * @Param: []
     * @Return: com.aaa.cloud.base.ResultData
     **/
    public ResultData selectAll(){
        List<T> tListResult = getBaseService().selectAll();
        if (null != tListResult && tListResult.size()>0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author: zcl
     * @Methodname: selectListByFiled
     * @Description: 返回一个列表 根据条件并排序
     * @Date: 2020/7/9 20:31
     * @Param: [where, orderByFiled, fileds]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData selectListByFiled(@RequestBody Sqls where, @RequestParam("orderByFiled") String orderByFiled, @RequestParam("fileds") String... fileds) {
        List<T> stResult = getBaseService().selectListByFiled(where, orderByFiled, fileds);
        if (null != stResult && stResult.size() > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

    /**
     * @Author: zcl
     * @Methodname: selectOneByFiled
     * @Description: 条件查询一条数据 并排序
     * @Date: 2020/7/9 20:34
     * @Param: [where, orderByFiled, fileds]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData selectOneByFiled(@RequestBody Sqls where, @RequestParam("orderByFiled") String orderByFiled, @RequestParam("fileds") String... fileds) {
        T tOneResult = getBaseService().selectOneByFiled(where, orderByFiled, fileds);
        if (null != tOneResult) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

    /**
     * @Author: zcl
     * @Methodname: selectOneByFiled
     * @Description: 条件分页查询
     * @Date: 2020/7/9 20:49
     * @Param: [pageNo, pageSize, where, orderByFiled, fileds]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData selectOneByFiled(@RequestParam("pageNo") Integer pageNo, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestBody Sqls where, @RequestParam("orderByFiled") String orderByFiled, @RequestParam("fileds") String... fileds) {
        PageInfo<T> pageInfoResult = getBaseService().selectListByPageAndFiled(pageNo, pageNo, where, orderByFiled, fileds);
        if (null != pageInfoResult && pageInfoResult.getSize()>0) {
            return super.operationSuccess();
        }
        return super.operationFailed();

    }

    /**
     * @Author: zcl
     * @Methodname: selectOneByFiled
     * @Description: 查询集合，分页返回
     * @Date: 2020/7/9 20:49
     * @Param: [map, pageNo, pageSize]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData selectOneByFiled(@RequestBody Map map, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        T t = getBaseService().newInstance(map);
        PageInfo<T> pageInfoResult = getBaseService().selectListByPage(t, pageNo, pageNo);
        if (null != pageInfoResult && pageInfoResult.getSize()>0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
    /**
     * @author Seven Lee
     * @description
     *      从本地当前线程中获取request对象
     * @param []
     * @date 2020/3/11
     * @return javax.servlet.http.HttpServletRequest
     * @throws
     **/
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @author Seven Lee
     * @description
     *      获取当前客户端session对象(如果没有则创建一个新的session)
     * @param []
     * @date 2020/3/11
     * @return javax.servlet.http.HttpSession
     * @throws
     **/
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

    /**
     * @author Seven Lee
     * @description
     *      获取当前客户端session对象(如果没有则直接返回null)
     * @param []
     * @date 2020/3/11
     * @return javax.servlet.http.HttpSession
     * @throws
     **/
    public HttpSession getExistSession() {
        return getServletRequest().getSession(false);
    }
}
