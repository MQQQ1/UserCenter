package com.yupi.usercenter2.common;

/**
 * ClassName：ResultUtil
 * Package:com.yupi.usercenter2.common
 * Description:返回工具类
 * Author：MQQQ
 *
 * @Create:2023/12/29 - 18:33
 * @Version:v1.0
 */
public class ResultUtils {
    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error (ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }
}
