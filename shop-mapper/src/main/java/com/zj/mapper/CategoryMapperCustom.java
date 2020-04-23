package com.zj.mapper;

import com.zj.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom{
    /**
     * @Description: 根据一级分类的id查询子分类
     * @param rootCatId:
     * @return: null
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/21
     */

    public List<CategoryVO> getSubCatList(Integer rootCatId);
}