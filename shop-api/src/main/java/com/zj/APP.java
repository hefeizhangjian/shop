package com.zj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj
 * @date 2020/4/15 16:31
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: Springboot启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zj.mapper")
public class APP {
    public static void main(String[] args) {
        SpringApplication.run(APP.class,args);


    }
}
