package com.zj.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.pojo.vo
 * @date 2020/4/21 23:17
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 二级分类vo
 */
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    private List<SubCategoryVO> subCatList;
}
