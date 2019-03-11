package com.ym.smdsj.controller;

import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.domain.vo.RequestResult;
import com.ym.smdsj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户数据的相关操作接口
 *
 * @author ym
 * @date 2019/3/8
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/list/")
    public RequestResult<List<AuthUser>> getUserList(){
        RequestResult<List<AuthUser>> requestResult = new RequestResult<>();
        try{
            List<AuthUser> authUsers = userService.getUserList();
            requestResult.success(200,"").addData(authUsers);
        }catch(Exception e){
            e.printStackTrace();
            requestResult.error(600,"查询数据异常");
        }
        return requestResult;


    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
