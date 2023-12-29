package com.yupi.usercenter2.common;

/**
 * ClassName：ErrorCode
 * Package:com.yupi.usercenter2.common
 * Description:错误码
 * Author：MQQQ
 *
 * @Create:2023/12/29 - 19:17
 * @Version:v1.0
 */
public enum ErrorCode {
    SUCCESS(0,"ok",""),
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求数据为空",""),
    NOT_LOGIN(40100,"未登录",""),
    NO_AUTN(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常","");
    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码描述
     */
    private final String descripton;

    ErrorCode(int code, String message, String descripton) {
        this.code = code;
        this.message = message;
        this.descripton = descripton;
    }

    public int getCode() {
        return code;
    }

    public  String getMessage() {
        return message;
    }

    public String getDescripton() {
        return descripton;
    }
}
