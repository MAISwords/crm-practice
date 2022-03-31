package com.xxxx.crm.controller;


import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.exceptions.ParamsException;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    //@PostMapping("user/login")
    @PostMapping("user/login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd){
        ResultInfo resultInfo = new ResultInfo();

        try{
            UserModel userModel = userService.userLogin(userName, userPwd);
            resultInfo.setResult(userModel);

        }catch (ParamsException e){
            e.printStackTrace();
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setCode(500);
            resultInfo.setMsg("操作失败！");
        }
        return resultInfo;




    }
}
