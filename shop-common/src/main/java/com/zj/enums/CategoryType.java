package com.zj.enums;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.enums
 * @date 2020/4/21 22:25
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 首页分类类型枚举
 */

public enum CategoryType {
    ONE(1,"一级分类"),
    TOW(2,"二级分类") ,
    THREE(3,"三级分类");

    public final Integer type;
   public final String value;
    CategoryType(Integer type,String value){
        this.type = type;
        this.value=value;
    }


}
