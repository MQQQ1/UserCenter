package com.yupi.usercenter2.common;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName：BaseResponse
 * Package:com.yupi.usercenter2.common
 * Description:通用返回类
 * Author：MQQQ
 *
 * @Create:2023/12/29 - 18:28
 * @Version:v1.0
 */
@Data
public class BaseResponse<T> implements Serializable {
    private int code;

    private T data;

    private String message;

    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;

    }
    public BaseResponse(int code, T data, String message){
        this(code, data, message, "");
    }
    public BaseResponse(int code, T data){
        this(code, data ,"");
    }
    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null, errorCode.getMessage(),errorCode.getDescripton());
    }
}
