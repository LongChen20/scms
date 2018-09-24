package com.scms.test;


import com.scms.dao.UserDao;
import com.scms.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class testDateSource {

    private ApplicationContext applicationContext = null;

    @Before
    public void getapplicationContext(){
        applicationContext = new ClassPathXmlApplicationContext("springIOC.xml");
    }

    @Test
    public void geource(){
       UserDao ud = (UserDao) applicationContext.getBean("userDao");
        User u = new User();
        u.setUsername("Lc");
        List<User> users  = ud.selectAllName();
        for (User user : users) {
            System.out.println(user);

        }
    }

    @Test
    public void testSelectByname(){
       UserDao ud = (UserDao) applicationContext.getBean("userDao");
        User u = ud.selectByname("Allen");
        System.out.println(u);
    }

    @Test
    public void selectByIdTest(){
        UserDao ud = (UserDao) applicationContext.getBean("userDao");
        User u = ud.selectById(2);
        System.out.println(u);
    }
}
