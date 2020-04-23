package com.zj.service;

import com.zj.pojo.Category;
import com.zj.pojo.vo.CategoryVO;

import java.util.List;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.service
 * @date 2020/4/21 22:15
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 分类服务接口
 */

public interface CategoryService {
    /**
     * @Description: 查询所有一级分类
     * @param :
     * @return: List<Category>
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/21
     */
    public List<Category> queryAllRootCategories();
    /**
     * @Description: 根据一级分类ID查询子分类
     * @param  rootCatId:
     * @return: null
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/21
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);
}
