package com.scms.test;

import com.scms.dao.CodeDao;
import com.scms.pojo.Code;
import com.scms.pojo.QueryVO;
import com.scms.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;


public class TestCodeDao {

    private ApplicationContext applicationContext = null;

    @Before
    public void getapplicationContext(){
        applicationContext = new ClassPathXmlApplicationContext("springIOC.xml");
    }

    @Test
    public void testSelectCodeAll(){
        CodeDao codedao = (CodeDao)applicationContext.getBean("codeDao");
        List<Code> codes = codedao.selectCodeAll();
        for (Code code : codes) {
            System.out.println(code);
        }
    }

    @Test
    public void testSelectCount(){
        CodeDao codedao = (CodeDao)applicationContext.getBean("codeDao");
        QueryVO vo = new QueryVO();
        vo.setFindkey("13");
        int i = codedao.selectFindCodeKey(vo);
        System.out.println(i);
    }

    @Test
    public void testSelectpageCodeAll(){
        CodeDao codedao = (CodeDao)applicationContext.getBean("codeDao");
        QueryVO vo = new QueryVO();
        vo.setFindkey("管理");
        List<Code> codes = codedao.selectFindCodeList(vo);
        for (Code code : codes) {
            System.out.println(code);
        }
    }

    @Test
    public void testInsertCode(){
        CodeDao codedao = (CodeDao)applicationContext.getBean("codeDao");
        Code code = new Code();
        code.setCodename("测试数据");
        code.setFilepath("/FF//DD");
        code.setIntro("测试类测试数据。");
        User user = new User();
        user.setId(1);
        code.setOwner(user);
        code.setAddTime(new Timestamp(System.currentTimeMillis()));
        codedao.insertCode(code);
    }
}
