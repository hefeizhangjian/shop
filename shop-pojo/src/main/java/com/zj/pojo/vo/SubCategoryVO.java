package com.zj.pojo.vo;

import lombok.Data;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.pojo.vo
 * @date 2020/4/21 23:22
 * @Copyright © 安徽华泓信息技术有限公司
 * @description:
 */
@Data
public class SubCategoryVO {
    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;

}
