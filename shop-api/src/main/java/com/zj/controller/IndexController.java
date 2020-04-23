package com.zj.controller;

import com.zj.enums.YesOrNo;
import com.zj.service.CarouselService;
import com.zj.service.CategoryService;
import com.zj.utils.ZjJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zj.utils.ZjJSONResult.errorMsg;
import static com.zj.utils.ZjJSONResult.ok;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.controller
 * @date 2020/4/20 15:13
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 通行证接口，如同UserController
 */
@RestController
@RequestMapping("index")
@Api(value = "首页",tags = {"首页展示的相关API"})
public class IndexController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/carousel")
    @ApiOperation(value = "首页轮播图",notes = "首页轮播图",httpMethod = "GET")
    public ZjJSONResult carousel(){

        return ok(carouselService.queryAll(YesOrNo.YES.type));
    }
    @GetMapping("/cats")
    @ApiOperation(value = "获取首页一级分类",notes = "获取首页一级分类",httpMethod = "GET")
    public ZjJSONResult cats(){

        return ok(categoryService.queryAllRootCategories());
    }
    @GetMapping("/subCat/{rootCatId}")
    @ApiOperation(value = "根据一级分类id获取子分类",notes = "根据一级分类id获取子分类",httpMethod = "GET")
    public ZjJSONResult subCats(@PathVariable
                                    @ApiParam(value = "一级分类的Id",name = "rootCatId",required = true)
                                            Integer rootCatId){
        if(rootCatId==null){
            return errorMsg("分类不存在");
        }
        return ok(categoryService.getSubCatList(rootCatId));
    }
}
