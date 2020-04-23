package com.zj.service.impl;

import com.zj.enums.Sex;
import com.zj.mapper.UsersMapper;
import com.zj.pojo.Users;
import com.zj.pojo.bo.UserBo;
import com.zj.service.UserService;
import com.zj.utils.DateUtil;
import com.zj.utils.MD5Utils;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.service.impl
 * @date 2020/4/20 15:01
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 默认用户头像
     */
    private static final String USER_FACE="http://pic.51yuansu.com/pic3/cover/02/48/29/59e629fca4e5d_610.jpg";
    @Autowired
    private UsersMapper usersMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUsernameIsExist(String username) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        Users users = usersMapper.selectOneByExample(example);
        return users != null;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        Users users = new Users();
        users.setId(Sid.nextShort());
        users.setUsername(userBo.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.setNickname(userBo.getUsername());
        users.setRealname(null);
        users.setFace(USER_FACE);
        users.setMobile(null);
        users.setEmail(null);
        users.setSex(Sex.secret.type);
        users.setBirthday(DateUtil.stringToDate("1970-01-01"));
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        usersMapper.insert(users);

        return users;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example example= new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        Users users = usersMapper.selectOneByExample(example);

        return users;
    }

}
