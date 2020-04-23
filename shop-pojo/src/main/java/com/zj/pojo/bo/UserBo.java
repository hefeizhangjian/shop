package com.zj.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.pojo.bo
 * @date 2020/4/20 15:58
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 前端请求的user对象的封装
 */
@Data
@ApiModel(value = "用户对象BO",description = "由前端传来的参数封装在此entity中")
public class UserBo {
    @ApiModelProperty(value = "用户名",name="username",required = true,example = "zj")
    private String username;
    @ApiModelProperty(value = "密码",name="password",required = true,example = "123123")
    private String password;
    @ApiModelProperty(value = "确认密码",name="confirmPassword",required = false,example = "123123")
    private String confirmPassword;
}
