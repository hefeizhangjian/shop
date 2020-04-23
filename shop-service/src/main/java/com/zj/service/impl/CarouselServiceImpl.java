package com.zj.service.impl;

import com.zj.mapper.CarouselMapper;
import com.zj.pojo.Carousel;
import com.zj.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.service.impl
 * @date 2020/4/21 21:45
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 轮播图service实现类
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    CarouselMapper carouselMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Carousel> queryAll(Integer isShow) {
        Example example=new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        return carouselMapper.selectByExample(example);
    }
}
