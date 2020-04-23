package com.zj.controller;

import com.zj.pojo.Users;
import com.zj.pojo.bo.UserBo;
import com.zj.service.UserService;
import com.zj.utils.CookieUtils;
import com.zj.utils.JsonUtils;
import com.zj.utils.ZjJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zj.utils.MD5Utils.getMD5Str;
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
@RequestMapping("passport")
@Api(value = "注册登录",tags = {"用于注册登录验证的API"})
public class PassportController {
    private final static int PASSWORD_LENGTH = 6;
    @Autowired
    private UserService userService;
    @GetMapping("usernameIsExist")
    @ApiOperation(value = "验证用户名是否存在",notes = "验证用户名是否存在",httpMethod = "GET")
    public ZjJSONResult usernameIsExist(@RequestParam String username){
        //1.判断用户名不能为空
        if(StringUtils.isBlank(username)){
            return errorMsg("用户名不能为空");
        }
        //2.查询注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return errorMsg("用户名已存在");
        }
        //3.请求成功，用户名不重复
        return ok();
    }
    @ApiOperation(value = "注册用户",notes = "注册用户",httpMethod = "POST")
    @PostMapping("/regist")
    public ZjJSONResult regist(@ApiParam(name = "userBo",value = "用户对象",
            required = true) @RequestBody UserBo userBo){

        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();

        //1.用户名或密码不能为空
        if(StringUtils.isBlank(username)
                || StringUtils.isBlank(password)){
            return errorMsg("用户名或密码不能为空");
        }
        //2.用户名已存在
        if(userService.queryUsernameIsExist(username)){
            return errorMsg("用户名已存在");
        }
        //3.密码长度不能少于6位
        if(password.length() < PASSWORD_LENGTH){
            return errorMsg("密码长度不能少于6位");
        }
        //4.确认密码必须和密码相同
        if(!password.equals(confirmPassword)){
            return errorMsg("两次密码必须相同");
        }
        //5.实现注册
        Users user = userService.createUser(userBo);
        return ok(user);
    }
    @ApiOperation(value = "用户登录",notes = "注册登录",httpMethod = "POST")
    @PostMapping("/login")
    public ZjJSONResult login(@ApiParam(name = "userBo",value = "用户对象",
            required = true) @RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response){

        String username = userBo.getUsername();
        String password = null;
        try {
            password = getMD5Str(userBo.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //1.用户名或密码不能为空
        if(StringUtils.isBlank(username)
                || StringUtils.isBlank(password)){
            return errorMsg("用户名或密码不能为空");
        }
        //2.实现注册
        Users user = userService.queryUserForLogin(username,password);
        if(null == user) {
            return errorMsg("用户名或密码错误,请查证后在登录");
        }
        user.setPassword(null);
        user.setUpdatedTime(null);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(user),true);
        return ok(user);
    }
    @ApiOperation(value = "退出登录",notes = "退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public ZjJSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response){
        //清除用户相关的cookie
        CookieUtils.deleteCookie(request,response,"user");
        //todo 需要清空购物车数据
        //todo 在分布式会话中需要清除用户数据
        return ok();
    }
}
