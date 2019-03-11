package com.ym.smdsj.controller;


import com.github.pagehelper.PageInfo;
import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.domain.vo.RequestResult;
import com.ym.smdsj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户数据的相关操作接口
 *
 * @author ym
 * @date 2019/3/8
 */
@Api(tags = {"用户操作接口"})
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;


    @ApiOperation(value = "获取用户列表", notes = "通过分页的方式获取所有注册用户的信息列表")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public RequestResult<PageInfo> getUserListByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        RequestResult<PageInfo> requestResult = new RequestResult<>();
        try {
            PageInfo<AuthUser> pageInfo = userService.getUserListByPageInfo(pageNum, pageSize);
            requestResult.addData(pageInfo).success(200, "");
        } catch (Exception e) {
            e.printStackTrace();
            requestResult.error(600, "查询数据异常");
        }
        return requestResult;


    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
