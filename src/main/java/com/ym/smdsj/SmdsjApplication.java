package com.ym.smdsj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 *
 * @author ym
 * @date 2019/3/4
 */
@SpringBootApplication
@MapperScan("com.ym.smdsj.dao")
public class SmdsjApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmdsjApplication.class, args);
    }

}
