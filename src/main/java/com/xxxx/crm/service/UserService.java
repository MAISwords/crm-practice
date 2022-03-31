package com.xxxx.crm.service;


import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.Md5Util;
import com.xxxx.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User, Integer> {

    @Resource
    private UserMapper userMapper;

    public UserModel userLogin(String userName, String userPwd){
        checkLoginParams(userName, userPwd);
        User user = userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(null == user, "用户不存在或已注销！");
        checkLoginPwd(userPwd, user.getUserPwd());
        return buildUserInfo(user);
    }

    private UserModel buildUserInfo(User user){
        UserModel userModel = new UserModel();
        userModel.setUserId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    private void checkLoginPwd(String userPwd, String upwd){
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(upwd), "用户密码不正确！");
    }

    private void checkLoginParams(String userName, String userPwd){
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户姓名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空！");
    }



}
