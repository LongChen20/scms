package com.scms.service;

import com.scms.dao.UserDao;
import com.scms.exception.MessageException;
import com.scms.exception.SystemOutputException;
import com.scms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao mapper;

    @Override
    public Map<String, Object> isresult(User loginUser) {

        Map<String, Object> result = new HashMap<String, Object>();
        User returnloginUser = null;
        //获得数据库返回的数据进行比对
        List<User> u = mapper.selectAllName();
        for (User user : u) {
//            查询出来的所有用户和用户登陆数据进行比较
            if (user.getUsername().equals(loginUser.getUsername().trim())) {
                if (user.getPassword().equals(loginUser.getPassword().trim())) {
                    result.put("islogin", true);
                    returnloginUser = user;
                    result.put("loginUser", returnloginUser);
                    break;
                } else {
                    result.put("message", "密码错误！");
                    result.put("islogin", false);
                    //密码错误不需要在进行比较退出循环，返回错误消息。
                    break;
                }
            } else {
                result.put("message", "用户名错误！");
                result.put("islogin", false);
            }
        }
        return result;
    }

    @Override
    public Boolean isname(User user) {
        User u = mapper.selectByname(user.getUsername());
        if (null != u) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Map<String, Object> insertUser(User user) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        user.setRank(2);
        user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        mapper.insertRegister(user);
        result.put("message", "注册成功");
        return result;
    }

    @Override
    public Map<String, Object> changepasswode(String password, String newpassword, HttpSession session) {
        Map<String, Object> ischangepass = new HashMap<>();
        User loginUser = (User) session.getAttribute("loginUser");
        User sqluser = mapper.selectById(loginUser.getId());
        if (!password.equals(sqluser.getPassword())) {
            ischangepass.put("message", "原始密码错误");
            ischangepass.put("isChange", false);
        } else {
            ischangepass.put("message", "修改成功");
            ischangepass.put("isChange", true);
            loginUser.setPassword(newpassword);
            mapper.updateChangePass(loginUser);
            ischangepass.put("loginUser", loginUser);
        }
        return ischangepass;
    }
}
