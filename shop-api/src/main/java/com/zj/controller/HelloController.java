package com.zj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.controller
 * @date 2020/4/15 16:35
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: hello
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
