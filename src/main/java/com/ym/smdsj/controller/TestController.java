package com.ym.smdsj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author ym
 * @date 2019/3/4
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/")
    public String ddd(){
        String ddd ="kkk";
        ddd = ddd.concat("222");
        return ddd;
    }
}
