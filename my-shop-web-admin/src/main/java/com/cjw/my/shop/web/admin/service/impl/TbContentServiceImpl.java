package com.cjw.my.shop.web.admin.service.impl;

import com.cjw.my.shop.domain.TbContent;
import com.cjw.my.shop.domain.TbUser;
import com.cjw.my.shop.web.admin.dao.TbContentDao;
import com.cjw.my.shop.web.admin.dao.TbUserDao;
import com.cjw.my.shop.web.admin.service.TbContentService;
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
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }


    @Override
    public BaseResult save(TbContent tbContent) {
        //用户信息非空验证
        BaseResult baseResult = checkTbContent(tbContent);
        //用户信息通过验证
        if(baseResult.getStatus() == BaseResult.SUCCESS_200){
            tbContent.setUpdated(new Date());
            //新增用户
            if(tbContent.getId() == null){
                tbContent.setCreated(new Date());
                tbContentDao.insert(tbContent);
            }
            //更新用户
            else{
                update(tbContent);
            }
            baseResult = baseResult.success("保存用户信息成功！");
        }

        return baseResult;
    }

    /**
     * 用户信息的有效性验证
     * @param tbContent
     * @return
     */
    public BaseResult checkTbContent(TbContent tbContent) {
        BaseResult baseResult = BaseResult.success();
        //非空验证
        if(StringUtils.isBlank(tbContent.getTitle())){
            baseResult = BaseResult.fail("标题不能为空，请重新输入！");
        }

        return baseResult;
    }

    @Override
    public TbContent selectById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw,TbContent tbContent) {
        PageInfo<TbContent> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        int count = count(tbContent);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        Map params = new HashMap<String,Object>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbContent",tbContent);
        pageInfo.setData(tbContentDao.page(params));
        return pageInfo;
    }

    @Override
    public Integer count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);
    }

    @Override
    public void delete(TbContent tbContent) {
        tbContentDao.delete(tbContent);
    }
}
