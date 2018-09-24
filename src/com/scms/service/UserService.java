package com.scms.service;

import com.scms.exception.MessageException;
import com.scms.exception.SystemOutputException;
import com.scms.pojo.User;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public interface UserService {

    /**
     * 用户登陆
     * @param loginuser
     * @return
     */
    Map<String, Object> isresult(User loginuser);

    /**
     * Ajax请求获得用户名是否重复。
     * @param user
     * @return
     */
    Boolean isname(User user);

    /**
     * 注册新用户
     * @param user
     */
    Map<String, Object> insertUser(User user) throws SQLException;

    /**
     * 修改密码
     * @param password
     * @param newpassword
     * @param session
     * @return
     */
    Map<String,Object> changepasswode(String password, String newpassword, HttpSession session);
}
