package com.lizhi.stpspringbootinit;

import cn.dev33.satoken.quick.SaQuickManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StpSpringbootInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(StpSpringbootInitApplication.class, args);

        System.out.println("\n------ 启动成功 ------");
        System.out.println("name: " + SaQuickManager.getConfig().getName());
        System.out.println("pwd:  " + SaQuickManager.getConfig().getPwd());

    }

}
