package com.yupi.usercenter2.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName：UserRegisterRequest
 * Package:com.yupi.usercenter2.model.domain.request
 * Description:用户登录 请求体
 * Author：MQQQ
 *
 * @Create:2023/12/29 - 15:29
 * @Version:v1.0
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 4555850124548307239L;

    private String userAccount;

    private String userPassword;

}
