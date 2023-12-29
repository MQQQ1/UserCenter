package com.yupi.usercenter2.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName：UserRegisterRequest
 * Package:com.yupi.usercenter2.model.domain.request
 * Description:用户注册 请求体
 * Author：MQQQ
 *
 * @Create:2023/12/29 - 15:29
 * @Version:v1.0
 */
@Data
public class UserRegisterRequest implements Serializable {

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String planetCode;
}
