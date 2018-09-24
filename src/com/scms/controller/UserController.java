package com.scms.controller;

import com.scms.annotation.LogerMessage;
import com.scms.exception.MessageException;
import com.scms.exception.SystemOutputException;
import com.scms.pojo.User;
import com.scms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LGCN
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    /**
     * 用户登陆
     * @param loginUser 封装数据。
     * @param model 添加到视图模型。
     * @param session 添加数据到session中
     * @return 返回提示
     */
    @LogerMessage(message = "登陆")
    @RequestMapping(value = "/login")
    public String login(User loginUser, Model model, HttpSession session) throws SystemOutputException {
        System.out.println(loginUser);
        session.removeAttribute("message");
        try {
            Map<String, Object> isSucceed = userService.isresult(loginUser);
            String message = (String) isSucceed.get("message");
            Boolean islogin = (Boolean) isSucceed.get("islogin");
            User returnLoginUser = (User) isSucceed.get("loginUser");
            if (islogin) {
                session.setAttribute("loginUser", returnLoginUser);
                return "redirect:/home";
            } else {
                session.setAttribute("message", message);
                return "/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemOutputException("登陆出现了不可预料的以外，请重新登陆吧！");
        }
    }

    /**
     * 退出登陆
     * @param session 从session中删除
     * @return 返回视图，去首页
     */
    @LogerMessage(message = "退出登陆")
    @RequestMapping("/loginout")
    public String loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/home";
    }

    /**
     * 获取用户名是否存在
     * @param user Ajax 转 pojo
     * @return 返回 Json数据。
     */
    @RequestMapping("/isusername")
    @ResponseBody
    public Map<String, Boolean> isUsername(@RequestBody User user) {
        Map<String, Boolean> isuser = new HashMap<>();
        Boolean returnname = userService.isname(user);
        isuser.put("username", returnname);
        return isuser;
    }

    /**
     * 注册用户
     * @param user 封装注册的数据。
     * @param session 用于消息提示。
     * @return 返回视图，是否注册成功
     * @throws MessageException 自定义异常，全局异常处理
     */
    @RequestMapping("/subimtregister")
    public String registerUser(User user,HttpSession session) throws MessageException {
        try {
            Map<String,Object> result = userService.insertUser(user);
            String msg = (String)result.get("message");
            session.setAttribute("message",msg);
            return "redirect:/bizzDone";
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("不小心迷路了，等等我。");
        }
    }

    /**
     * 修改密码
     * @param password 填写的旧密码(用于登陆的密码)
     * @param newpassword 新密码
     * @param session 消息提示。
     * @return 返回视图。
     */
    @LogerMessage(message = "修改了密码")
    @RequestMapping("/changePassword")
    public String changePass(String password, String newpassword, HttpSession session) throws SystemOutputException {
        try {
            int i = 1/0;
            Map<String, Object> resouct = userService.changepasswode(password, newpassword, session);
            String msg = (String) resouct.get("message");
            Boolean isChange = (Boolean) resouct.get("isChange");
            User loginUser = (User) resouct.get("loginUser");
            if (isChange) {
                session.setAttribute("message", msg);
                session.setAttribute("loginUser", loginUser);
                session.setAttribute("issuecces",isChange);
                return "/bizzDone";
            } else {
                //标记石否更新成功。
                session.setAttribute("issuecces",isChange);
                session.setAttribute("message", msg);
                return "/bizzDone";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemOutputException("小的正在努力修复BUG！");
        }
    }
}
