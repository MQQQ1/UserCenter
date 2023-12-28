package com.yupi.usercenter2.service;

import com.yupi.usercenter2.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author MQ
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-12-28 22:59:44
*/
public interface UserService extends IService<User> {

    /**
     * 用户注释
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
