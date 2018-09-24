package com.scms.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆检查拦截器
 * @author LGCN
 */
public class LoginInterceptors extends HandlerInterceptorAdapter  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得亲求的url
        String url = request.getServletPath();
        String method = request.getMethod();
        //equalsIgnoreCase比较内容忽略大小写，contains包含指定内容。
        if ("get".equalsIgnoreCase(method) && url.contains ("/code/codes")){
            if (null == request.getSession().getAttribute("loginUser")){
                //域中没有取到值则为空没登陆，不放行。
                    response.sendRedirect("/not_login");
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }
}
