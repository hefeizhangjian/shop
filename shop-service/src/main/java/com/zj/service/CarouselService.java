package com.zj.service;

import com.zj.pojo.Carousel;

import java.util.List;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.service
 * @date 2020/4/21 21:42
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 轮播图
 */

public interface CarouselService {
    /**
     * @Description: 查询所有轮播图列表
     * @param isShow:
     * @return: List<Carousel>
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/21
     */
    public List<Carousel> queryAll(Integer isShow);
}
