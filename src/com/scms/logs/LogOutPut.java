package com.scms.logs;

import com.scms.annotation.LogerMessage;
import com.scms.pojo.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogOutPut {

    private static Logger logger = Logger.getLogger(LogOutPut.class);


    @Around("execution(* com.scms.controller.*.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        //获得注解的数据。
        String methodMsg = getAnnotationmsg(pjp);
        //获得当前调用的方法。
        String thisName = pjp.getSignature().getName();
        //用于返回
        Object obj = null;
        //存储用户名。
        String username = null;
        //判断是否是退出登陆，是先获得用户名在执行退出方法。
        if (thisName.equalsIgnoreCase("loginOut")) {
            //先拿用户名，
            username = getUserName();
            obj = pjp.proceed();
        } else {
            obj = pjp.proceed();
            username = getUserName();
        }
        if (null != methodMsg) {
            System.out.println("***" + username + " " + methodMsg + "***");
            logger.info("*****" + username + " " + methodMsg + "*****");
        }
        return obj;
    }


    /**
     * 得到用户名
     *
     * @return
     */
    public static String getUserName() {
        //获得HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("loginUser");
        if (null != user) {
            return user.getUsername();
        } else {
            return "登陆失败";
        }
    }

    /**
     * 获得注解上面的数据。
     *
     * @param jp
     * @return
     */
    public String getAnnotationmsg(JoinPoint jp) {
        //获得连接点方法签名对象。
        Signature signature = jp.getSignature();
        String methodName = signature.getName();
        //获得所有的方法名进行比对
        Method[] methods = signature.getDeclaringType().getMethods();
        for (Method method : methods) {
            //判
            if (method.getName().equals(methodName)) {
                //判断方法上是否有指定类的注解。
                if (method.isAnnotationPresent(LogerMessage.class)) {
                    // 如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。
                    LogerMessage lm = method.getAnnotation(LogerMessage.class);
                    return lm.message();
                }
            }

        }
        return null;
    }

}
