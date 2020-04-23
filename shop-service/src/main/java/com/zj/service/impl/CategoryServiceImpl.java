package com.zj.service.impl;

import com.zj.enums.CategoryType;
import com.zj.mapper.CategoryMapper;
import com.zj.mapper.CategoryMapperCustom;
import com.zj.pojo.Category;
import com.zj.pojo.vo.CategoryVO;
import com.zj.service.CategoryService;
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
 * @date 2020/4/21 22:19
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: CategoryService实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryMapperCustom categoryMapperCustom;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAllRootCategories() {
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", CategoryType.ONE.type);

        return categoryMapper.selectByExample(example);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }
}
