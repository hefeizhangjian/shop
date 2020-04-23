package com.zj.service;

import com.zj.pojo.Users;
import com.zj.pojo.bo.UserBo;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.service
 * @date 2020/4/20 14:56
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: UserService接口
 */

public interface UserService {
    /**
     * @Description: 判断用户名是否存在
     * @param username:
     * @return: boolean
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/20
     */
    public boolean queryUsernameIsExist(String username);
    /**
     * @Description: 创建用户
     * @param userBo:
     * @return: Users
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/20
     */
    public Users createUser(UserBo userBo);
    /**
     * @Description: 用于用户登录
     * @param username: 用户名
     * @param password: 密码
     * @return: Users
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/21
     */
    public Users queryUserForLogin(String username,String password);


}
