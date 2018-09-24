package com.scms.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest Request, HttpServletResponse Response, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        if (e instanceof MessageException){
            StringBuffer sb = new StringBuffer();
            sb.append("<p>"+e.getMessage()+"</p>");
            sb.append("<p>您可以联系管理员,也可以返回<a href='"+Request.getContextPath()+"/home'>首页</a></p>");
            mv.addObject("message", sb.toString());
            mv.addObject("issuecces", false);
            mv.setViewName("/bizzDone");
        }if(e instanceof SystemOutputException){
            StringBuilder sb = new StringBuilder();
            sb.append("<p>"+e.getMessage()+"</p>");
            sb.append("<p>您可以联系管理员,也可以返回<a href='"+Request.getContextPath()+"/home'>首页</a></p>");
            mv.addObject("message", sb.toString());
            mv.setViewName("systemerror");
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append("<p>您可以联系管理员,也可以返回<a href='"+Request.getContextPath()+"/home'>首页</a></p>");
            mv.addObject("message", sb.toString());
            mv.setViewName("systemerror");
            mv.setViewName("BizzError");
        }
        return mv;
    }
}
