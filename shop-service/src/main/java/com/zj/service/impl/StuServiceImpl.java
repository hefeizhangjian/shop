package com.zj.service.impl;

import com.zj.mapper.StuMapper;
import com.zj.pojo.Stu;
import com.zj.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.service.impl
 * @date 2020/4/16 11:16
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: Stu服务实现类
 */
@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }



}
