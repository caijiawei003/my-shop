package com.cjw.my.shop.web.admin.test;

/**
 * @program:my-shop
 * @description:单元测试
 * @author:xxs_cjw
 * @create:2020-02-03 15:07
 **/

import com.cjw.my.shop.domain.TbUser;
import com.cjw.my.shop.web.admin.dao.TbUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserDao tbUserDao;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserDao.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@admin.com");
        String pwd = DigestUtils.md5DigestAsHex("admin".getBytes());
        tbUser.setPassword(pwd);
        tbUser.setPhone("15888888888");
        tbUser.setUsername("cjw");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserDao.insert(tbUser);
    }

    @Test
    public void testDelete() {
        TbUser tbUser = new TbUser();
        tbUser.setId(38L);

        tbUserDao.delete(tbUser);
    }

    @Test
    public void testGetById() {
        TbUser tbUser = tbUserDao.getById(36L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testUpdate() {
        TbUser tbUser = tbUserDao.getById(36L);
        tbUser.setUsername("Lusifer");

        tbUserDao.update(tbUser);
    }

    @Test
    public void testSelectByName() {
        List<TbUser> tbUsers = tbUserDao.selectByName("uni");
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }
}