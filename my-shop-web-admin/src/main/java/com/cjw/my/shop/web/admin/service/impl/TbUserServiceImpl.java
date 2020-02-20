package com.cjw.my.shop.web.admin.service.impl;

import com.cjw.my.shop.domain.TbUser;
import com.cjw.my.shop.web.admin.dao.TbUserDao;
import com.cjw.my.shop.web.admin.service.TbUserService;
import com.my.shop.commons.dto.BaseResult;
import com.my.shop.commons.dto.PageInfo;
import com.my.shop.commons.utils.RegexpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:my-shop
 * @description:
 * @author:xxs_cjw
 * @create:2020-01-15 16:21
 **/
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if(tbUser != null){
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            //判断加密后的密码和数据库中额密码是否相等，相等即登录
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);
        //用户信息通过验证
        if(baseResult.getStatus() == BaseResult.SUCCESS_200){
            tbUser.setUpdated(new Date());
            //新增用户
            if(tbUser.getId() == null){
                //密码加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }
            //更新用户
            else{
                tbUserDao.update(tbUser);
            }
            baseResult = baseResult.success("保存用户信息成功！");
        }

        return baseResult;
    }

    /**
     * 用户信息的有效性验证
     * @param tbUser
     * @return
     */
    public BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();
        //非空验证
        if(StringUtils.isBlank(tbUser.getEmail())){
            baseResult = BaseResult.fail("邮箱不能为空，请重新输入！");
        }
        else if(!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseResult = BaseResult.fail("邮箱格式不正确，请重新输入！");
        }
        else if(StringUtils.isBlank(tbUser.getPassword())){
            baseResult = BaseResult.fail("密码不能为空，请重新输入！");
        }
        else if(StringUtils.isBlank(tbUser.getUsername())){
            baseResult = BaseResult.fail("姓名不能为空，请重新输入！");
        }
        else if(StringUtils.isBlank(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机不能为空，请重新输入！");
        }
        else if(!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机格式不正确，请重新输入！");
        }

        return baseResult;
    }

    @Override
    public TbUser selectById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length, int draw,TbUser tbUser) {
        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        int count = count(tbUser);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        Map params = new HashMap<String,Object>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbUser",tbUser);
        pageInfo.setData(tbUserDao.page(params));
        return pageInfo;
    }

    @Override
    public Integer count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }
}
