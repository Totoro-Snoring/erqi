package com.aaa.cloud.base;

import com.aaa.cloud.utils.Map2BeanUtils;
import com.aaa.cloud.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.aaa.cloud.staticproperties.OrderStatic.*;

/**
 * @Author: zcl
 * @ClassName: BaseService
 * @Description: 通用Service
 * abstract :抽象类  被<T>Service继承,可以让 <T>Service子类选择性重写方法
 * BaseService<T>  BaseService<User>,BaseService<Book>,BaseService<Order>....
 * @Date: 2020/7/8 16:24
 */
public abstract class BaseService<T> {

//    public abstract BaseMapper<T> getBaseMapper();

    private Class<T> cache = null;

    @Autowired
    private Mapper<T> mapper;
    /*
    *private Mapper<T> mapper; 注入通用Mapper ,为了保证安全性 必须是private类型
    *
    *  protected Mapper getMapper(){
        return mapper;
        * 对外提供接口 ，给子类使
      * 作用范围：        当前类   同一个包   其他package          同包子孙类           不同包子孙类
     * public           可以      可以        可以                 可以                 可以
     * protected        可以      可以        不可以               可以                 可以
     * default          可以      可以        不可以               可以                 不可以
     * private          可以      不可以       不可以              不可以               不可以
    }
     */

    protected  Mapper getMapper() {
        return mapper;
    }

    /**
     * @Author: zcl
     * @Methodname: insertDate
     * @Description: 通用添加数据方法
     * @Date: 2020/7/8 16:38
     * @Param: [t]
     * @Return: com.aaa.cloud.base.ResultData
     */
    public Integer add(T t) {
        return mapper.insert(t);
    }

    /**
     * @Author: zcl
     * @Methodname: deleteByPrimaryKey
     * @Description: 通过主键删除， 主键可能是ID,username ,age...
     * @Date: 2020/7/9 9:11
     * @Param: [t]
     * @Return: java.lang.Integer
     */
    public Integer deleteByPrimaryKey(T t) {
        return mapper.deleteByPrimaryKey(t);
    }

    /**
     * @Author: zcl
     * @Methodname: dedeleteByObject
     * @Description: 根据主键批量删除
     * delete * from user where username = zhangsan and in (1,2,3,4,5,6)
     * andIn("id")--->id就是数据库中的主键名称
     * Sqls.custom()---> 返回新的Sql
     * @Date: 2020/7/9 9:14
     * @Param: []
     * @Return: java.lang.Integer
     */
    public Integer deleteByIds(List<Integer> ids) {
        //1.Sql语句拼接 2.构建成一个类传入通用Mapper的方法中
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", ids)).build();
        return mapper.deleteByPrimaryKey(example);
    }


    /**
     * @Author: zcl
     * @Methodname: update
     * @Description: 更新操作
     * updateByPrimaryKey<Object var1>对实体类的字段全部更新（不判断是否为Null），即如果字段为空就更新为空；
     * updateByPrimaryKeySelective<Object var1>会对实体类字段进行判断再更新(如果为Null就忽略更新)，如果字段为空，忽略不更新；
     * updateByExample<T t,Object var1>需要将表的条件全部给出，比如一个表有三个字段，就必须给三个字段给他，不给会设为null
     * updateByExampleSelective<T t,Object var1>不同，当某一实体类的属性为null时，mybatis会使用动态sql过滤掉，不更新该字段：通过这个类可以实现 order by 和一部分的where 条件
     * @Date: 2020/7/9 16:20
     * @Param: [t]
     * @Return: java.lang.Integer
     */
    public Integer update(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @Author: zcl
     * @Methodname: batchUpdate
     * @Description: 批量更新
     * Arrays.asList(数组)该方法是将数组转化为list。
     * @Date: 2020/7/9 16:45
     * @Param: [t, ids]
     * @Return: java.lang.Integer
     */
    public Integer batchUpdate(T t, Integer[] ids) {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExampleSelective(t, example);

    }

    /**
     * @Author: zcl
     * @Methodname: selectOne
     * @Description: 查询一条数据
     * @Date: 2020/7/9 16:58
     * @Param: [t]
     * @Return: T      一般代表实体类
     */
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    /**
     * @Author: zcl
     * @Methodname: selectAll
     * @Description:
     *         查询所有数据
     * @Date: 2020/7/16 11:44
     * @Param: []
     * @Return: java.util.List<T>
     **/
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * @Author: zcl
     * @Methodname: selectOneByFiled
     * @Description: 根据fileds查询一条数据 并排序
     * fileds 可以是id,username ,password ,age
     * select * from user where password = xxxx and age = xx and address = xxx
     * @Date: 2020/7/9 17:05
     * @Param: [where, orderByFiled, fileds]
     * @Return: T
     */
    public T selectOneByFiled(Sqls where, String orderByFiled, String... fileds) {
        return selectByFileds(null, null, where, orderByFiled, null, fileds).get(0);
    }

    /**
     * @Author: zcl
     * @Methodname: selectListByFiled
     * @Description: 返回一个列表 根据条件并排序
     * @Date: 2020/7/9 18:15
     * @Param: [where, orderByFiled, fileds]
     * @Return: java.util.List<T>
     */
    public List<T> selectListByFiled(Sqls where, String orderByFiled, String... fileds) {
        return selectByFileds(null, null, where, orderByFiled, null, fileds);
    }

    /**
     * @Author: zcl
     * @Methodname: selectListByPageAndFiled
     * @Description: 条件分页查询
     * PageInfo<T>
     * @Date: 2020/7/9 18:17
     * @Param: [pageNo, pageSize, where, orderByFiled, fileds]
     * @Return: java.util.List<T>
     */
    public PageInfo<T> selectListByPageAndFiled(Integer pageNo, Integer pageSize, Sqls where, String orderByFiled, String... fileds) {
        return new PageInfo<T>(selectByFileds(pageNo, pageSize, where, orderByFiled, null, fileds));
    }

    /**
     * @Author: zcl
     * @Methodname: select
     * @Description:
     *          查询集合，条件查询
     * @Date: 2020/7/9 18:23
     * @Param: [t]
     * @Return: java.util.List<T>
     */

    public List<T> select(T t) {
        return mapper.select(t);
    }

    /**
      * @Author: zcl
      * @Methodname: selectListByPage
      * @Description:
     *          查询集合，分页返回
      * @Date: 2020/7/9 18:28
      * @Param: [t, pageNo, pageSize]
      * @Return: com.github.pagehelper.PageInfo<T>
     */
    public PageInfo<T> selectListByPage(T t, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> selectList = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<T>(selectList);
        return pageInfo;
    }

    /**
     * @Author: zcl
     * @Methodname: selectByFileds
     * @Description: 通用查询方法  可用于分页，排序 ，条件查询
     * PageHelper :
     * select * from user where age = 18 and deptno = 20 order by desc/asc
     * @Date: 2020/7/9 17:21
     * @Param: [pageNo, pageSize, where, orderByFiled, orderWord, fileds]
     * @Return: java.util.List<T>
     */
    private List<T> selectByFileds(Integer pageNo, Integer pageSize, Sqls where, String orderByFiled, String orderWord, String... fileds) {
        Example.Builder builder = null;
        if (null == fileds || fileds.length == 0) {
            //不需要条件查询 ，查询所有数据
            Example.builder(getTypeArguement());
        } else {
            //说明需要进行条件查询
            builder = Example.builder(getTypeArguement()).select(fileds);
        }
        if (where != null) {
            //连接条件查询
            builder = builder.where(where);
        }
        if (orderByFiled != null) {
            //需要对某个字段进行排序
            if (DESC.equals(orderWord.toUpperCase())) {
                builder = builder.orderByDesc(orderByFiled);
            } else if (ASC.equals(orderWord.toUpperCase())) {
                builder = builder.orderByAsc();
            } else {
                builder = builder.orderByDesc(orderByFiled);
            }
        }
        Example example = builder.build();
        if (pageNo != null & pageSize != null) {
            //分页
            PageHelper.startPage(pageNo, pageSize);
        }
        //返回查询结果
        return getMapper().selectByExample(example);
    }

    /**
      * @Author: zcl
      * @Methodname: newInstance
      * @Description:
     *          map集合转换成实体类
      * @Date: 2020/7/9 18:33
      * @Param: [map]   前端传来的JSON数据 和 map 格式相同
      * @Return: T      实体类
     */
    public T newInstance(Map map){
        return (T) Map2BeanUtils.map2Bean(map,getTypeArguement());
    }
    /**
     * @Author: zcl
     * @Methodname: getTypeArguement
     * @Description: 获取子类的泛型类型
     * ParameterizedType就是参数化类型的意思,声明类型中带有“<>”的都是参数化类型，比如List<Integer>，Map<String,Long>
     * getActualTypeArguments()返回Type[]，即“<>”里的参数，比如Map<String,Integer>   获得String，Integer
     * getRawType()返回Tpye，得到“<>”前面的类型，比如List<String> 获得List
     * getOwnerType()返回Type，O<T>.I<S>类型变量调用时会返回O<T>，比如Map.Entry<Long,Short> 获得Map
     * <p>
     * 含泛型父类Father<T>, 继承了含泛型父类的子类Son
     * getClass（自身类）        自己类 :class com.mark.test.Son
     * getSuperclass（父类）    父类 :class com.mark.test.Father
     * getGenericSuperclass（带有泛型的父类，原返回值类型为Type，需要强转类型）  带有泛型的父类:com.mark.test.Father<com.mark.test.Son>
     * 高转低(ParameterizedType) this.getClass.getGenericSuperclass();
     * @Date: 2020/7/9 15:16
     * @Param: []
     * @Return: java.lang.Class<T>
     */
    public Class<T> getTypeArguement() {
        if (null == cache) {
            //getClass().getGenericSuperclass()返回表示此 Class 所表示的bai实体（类、接口、基本类型du或 void）的直接超类的zhi
            // Type然后将其转换ParameterizedType。。getActualTypeArguments()返回表示此类型实际类型参数dao的 Type 对象的数组。
            // [0]就是这个数组中第一个了。。简而言之就是获得超类的泛型参数的实际类型。。
            cache = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return cache;
    }

    /**
    * @Author: zcl
    * @ClassName: BaseService
    * @Description:
     *           *      获取spring容器/获取spring的上下文
     *      *      在项目开始运行的时候，会去加载spring配置，
     *      *      如果你们项目需要在项目启动的时候也加载自己的配置文件
     *      *      在spring的源码中有一个必须要看的方法(init())
     *      *      init()--->就是在项目启动的时候去加载spring的配置
     *      *       如果你的项目中也需要把某一些配置一开始就托管给spring
     *      *       需要获取到spring的上下文(ApplicationContext)
    * @Date: 2020/7/9 18:51
    */
    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }
}
