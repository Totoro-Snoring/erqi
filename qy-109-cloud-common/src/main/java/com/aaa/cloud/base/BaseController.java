package com.aaa.cloud.base;

import com.aaa.cloud.status.CRUDStatus;

import java.util.List;

import static com.aaa.cloud.status.CRUDStatus.*;
import static com.aaa.cloud.status.LoginStatus.*;
import static com.aaa.cloud.status.OperationStatus.FAILED;
import static com.aaa.cloud.status.OperationStatus.SUCCESS;


/**
* @Author: zcl
* @ClassName: BaseController
* @Description:
 *              统一Controller返回值类型
 *              让所有<T>Controller继承该类，进行统一类型返回
 *
 *      eg:
 *          登录成功和失败
 *          把状态码封装成 “枚举类”，减少项目中的硬编码
 *          code:200 msg:登录成功
 *          code:400 msg:登录失败，系统异常
 *          code:201 msg:用户已经存在
 *          code:401 msg:用户不存在
 *          code:402 msg:密码错误
 *          code:405 msg:用户退出异常
 *
 *          ResultData resultData = new ResultData();
 *          resultData.setCode(LOGIN_SUCCESS.getCode()); =  resultData.setCode("200");
 *          resultData.setMsg(LOGIN_SUCCESS.getCode());  =  resultData.setMsg("登录成功");
 *          return resultData;
 *
* @Date: 2020/7/8 15:24
*/
public class BaseController {

    /**
      * @Author: zcl
      * @Methodname: loginSuccess
      * @Description:   登录成功，返回系统消息
      * @Date: 2020/7/8 15:43
      * @Param: []
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData loginSuccess(){
        ResultData resultData = new ResultData();
         resultData.setCode(LOGIN_SUCCESS.getCode());
         resultData.setMsg(LOGIN_SUCCESS.getCode());
         return resultData;
    }

    /**
      * @Author: zcl
      * @Methodname: loginFailed
      * @Description:
     *          登录成功
     *          自定义返回消息
      * @Date: 2020/7/8 15:45
      * @Param: [msg]
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData loginSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

/**
  * @Author: zcl
  * @Methodname: loginSuccess
  * @Description:
  *         登录成功
 *          返回数据，使用系统消息
  * @Date: 2020/7/8 15:49
  * @Param: [data]
  * @Return: com.aaa.cloud.base.ResultData
 */
    public ResultData loginSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
      * @Author: zcl
      * @Methodname: loginSuccess
      * @Description:
     *          登录成功
     *          返回数据，自定义消息
      * @Date: 2020/7/8 15:52
      * @Param: [data, msg]
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData loginSuccess(Object data,String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
      * @Author: zcl
      * @Methodname: loginFailed
      * @Description:
     *          登录失败
     *          返回系统消息
      * @Date: 2020/7/8 15:54
      * @Param: []
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData loginFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }

    /**
      * @Author: zcl
      * @Methodname: loginFailed
      * @Description:
     *          登录失败
     *          返回详细说明，使用系统消息
      * @Date: 2020/7/8 15:56
      * @Param: [detail]
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData loginFailed(String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }


    /**
      * @Author: zcl
      * @Methodname: loginFailed
      * @Description:
     *          登录失败
     *          返回详细说明，自定义消息
      * @Date: 2020/7/9 8:06
      * @Param: [msg, detail]
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData loginFailed(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);

        return resultData;
    }


    /**
      * @Author: zcl
      * @Methodname: loginFailed
      * @Description:
     *          登录失败
     *          返回数据，自定义消息
      * @Date: 2020/7/9 8:08
      * @Param: [msg, data]
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData loginFailed(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
      * @Author: zcl
      * @Methodname: operationSuccess
      * @Description:
     *          操作成功 返回系统消息
      * @Date: 2020/7/9 19:34
      * @Param: []
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData operationSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getCode());
        return resultData;
    }

    /**
      * @Author: zcl
      * @Methodname: operationFailed
      * @Description:
     *          操作失败，返回系统消息
      * @Date: 2020/7/9 19:35
      * @Param: []
      * @Return: com.aaa.cloud.base.ResultData
     */
    public ResultData operationFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getCode());
        return resultData;
    }


    /**
     * @Author: zcl
     * @Methodname: selectSuccess
     * @Description:
     *         查询成功，返回系统消息
     * @Date: 2020/7/17 10:54
     * @Param: []
     * @Return: com.aaa.cloud.base.ResultData
     **/
    public ResultData selectSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_SUCCESS.getCode());
        resultData.setMsg(SELECT_SUCCESS.getMsg());
        return resultData;
    }
    public ResultData selectFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_FAILED.getCode());
        resultData.setMsg(SELECT_FAILED.getMsg());
        return resultData;
    }
    /**
     * @Author: zcl
     * @Methodname: selectSuccess
     * @Description:
     *         查询成功，返回查询数据
     * @Date: 2020/7/17 10:53
     * @Param: [data]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    public ResultData selectSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_SUCCESS.getCode());
        resultData.setMsg(SELECT_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    public ResultData selectFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_FAILED.getCode());
        resultData.setMsg(SELECT_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    public ResultData addSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_SUCCESS.getCode());
        resultData.setMsg(ADD_SUCCESS.getMsg());
        return resultData;
    }
    public ResultData addSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_SUCCESS.getCode());
        resultData.setMsg(ADD_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    public ResultData addFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_FAILED.getCode());
        resultData.setMsg(ADD_FAILED.getMsg());
        return resultData;
    }
    public ResultData delSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(DELETE_SUCCESS.getMsg());
        return resultData;
    }
    public ResultData delSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(DELETE_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    public ResultData delFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        return resultData;
    }
    public ResultData updateSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(UPDATE_SUCCESS.getMsg());
        return resultData;
    }
    public ResultData updateSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(UPDATE_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    public ResultData updateFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        return resultData;
    }
    // TODO 代码未完善，记得补充
}
