package com.aaa.cloud.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: zcl
 * @ClassName: TokenVo
 * @Description: token view object
 * @Date: 2020/7/15 15:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVo implements Serializable {
    private String token;
    private Boolean ifSucess;

    /**
    1.账户不存在
    2.密码错误
    3.账户被锁定
    4。系统异常
     */
    private Integer type;
}
