package com.aaa.cloud.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @Author: zcl
* @ClassName: ResultData
* @Description:
 *              通过接口返回值，把后端的controller的返回值统一成一种类型
 *
 *              <T>  :泛型，相当于一个占位符，代表任何类型 Book,User,Order...
* @Date: 2020/7/8 15:18
*/
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;

}
