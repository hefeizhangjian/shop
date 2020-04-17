package com.zj.controller;

import com.zj.pojo.Stu;
import com.zj.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.controller
 * @date 2020/4/15 16:35
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: hello
 */
@RestController
public class StuFooController {
    @Autowired
    StuService stuService;
    @GetMapping("/getStu")
    public Stu getStu(int id){

        Stu stuInfo = stuService.getStuInfo(id);
        System.out.println(stuInfo);
        return stuInfo;
    }
}
