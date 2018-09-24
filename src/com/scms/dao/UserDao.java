package com.scms.dao;

import com.scms.pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有的用户
     * @return
     */
    List<User> selectAllName();

    /**
     * 发起Ajax请求 获得username是否重复
     * @param name 字符串
     * @return
     */
    User selectByname(String name);

    /**
     * 注册
     * @param user
     */
    void insertRegister(User user );

    /**
     * 查询指定ID的数据。
     * @param id
     * @return
     */
   User selectById(Integer id);

    /**
     * 修改密码
     * @param user
     */
   void updateChangePass(User user);

}
