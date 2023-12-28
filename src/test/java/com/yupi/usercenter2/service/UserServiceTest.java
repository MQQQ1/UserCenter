package com.yupi.usercenter2.service;

import com.yupi.usercenter2.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName：UserServiceTest
 * Package:com.yupi.usercenter2.service
 * Description:
 * Author：MQQQ
 *
 * @Create:2023/12/28 - 23:53
 * @Version:v1.0
 */
@SpringBootTest
public  class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAdduser(){
        User user = new User();
        user.setUsername("MQQQ");
        user.setUserAccount("123");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setEmail("187");
        user.setPhone("2423");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);

    }

    /**
     * 测试注册功能
     */
    @Test
    void userRegister() {

        //测试非空
        String userAccount = "MQQQ";
        String userPassword = "";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //测试账户长度小于4
        userAccount = "MQQ";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        //测试密码小于6位
        userAccount = "MQQQ";
        userPassword = "12345";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //测试特殊字符
        userAccount = "MQQQ@";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //测试密码和校验密码不相同
        userAccount = "MQQQ";
        checkPassword = "1234567899";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //测试账号不重复
        userAccount = "MQQQ";
        userPassword = "";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //插入数据
        userAccount = "MQQQ";
        userPassword = "123456789";
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);
    }

}