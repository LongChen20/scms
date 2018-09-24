package com.scms.listeners;

import com.scms.pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

/**
 * 在线人数监听
 * @author LGCN
 */
public class OnlineNumberListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //得到application域
        HttpSession session = httpSessionEvent.getSession();
        ServletContext application = session.getServletContext();
        Integer online = (Integer) application.getAttribute("online");
        if (online == null) {
            // 第一次创建
            online = new Integer(1);
        } else {
            online = new Integer(online + 1);
        }
        application.setAttribute("online", online);
//        session.invalidate(); session只有在调用粗方法或超时才会调用session销毁方法.
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //销毁
        ServletContext application = httpSessionEvent.getSession().getServletContext();

        Integer online = (Integer) application.getAttribute("online");
        System.out.println("销毁------");
        if (online == null){
            application.setAttribute("online",0);
        }else {
            application.setAttribute("online",(online-1));
        }
    }
}
