package com.yupi.usercenter2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter2.common.ErrorCode;
import com.yupi.usercenter2.exception.BusinessException;
import com.yupi.usercenter2.service.UserService;
import com.yupi.usercenter2.model.domain.User;
import com.yupi.usercenter2.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yupi.usercenter2.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author MQ
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-12-28 22:59:44
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private  UserMapper userMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "yupi";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode) {

        // 1.校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        //账号不能小于4位
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号过短");
        }
        //密码不能小于8位
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过短");
        }
        //星球编号不能为空且小于5
        if(planetCode.length() > 5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号过长");
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }

        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号重复");
        }
        //星球编号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        count = userMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号重复");
        }

        // 2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {

        // 1.校验
        // todo 修改为自定义异常
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8){
            return null;
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        // 2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount Cannot match userPassword");
            return null;
        }
        // 3.用户脱敏
        User safetyUser = getSafetyUser(user);
        // 4.记录用户的登录状态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }
    @Override
    public User getSafetyUser(User origginUser){
        if(origginUser == null){
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(origginUser.getId());
        safetyUser.setUsername(origginUser.getUsername());
        safetyUser.setUserAccount(origginUser.getUserAccount());
        safetyUser.setAvatarUrl(origginUser.getAvatarUrl());
        safetyUser.setGender(origginUser.getGender());
        safetyUser.setEmail(origginUser.getEmail());
        safetyUser.setUserRole(origginUser.getUserRole());
        safetyUser.setPlanetCode(origginUser.getPlanetCode());
        safetyUser.setUserStatus(origginUser.getUserStatus());
        safetyUser.setPhone(origginUser.getPhone());
        safetyUser.setCreateTime(origginUser.getCreateTime());
        return safetyUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        //移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

}




