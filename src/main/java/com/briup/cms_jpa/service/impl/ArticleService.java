package com.briup.cms_jpa.service.impl;

import com.briup.cms_jpa.bean.Article;
import com.briup.cms_jpa.bean.Category;
import com.briup.cms_jpa.dao.ArticleDao;
import com.briup.cms_jpa.dao.CategoryDao;
import com.briup.cms_jpa.exception.CustomerException;
import com.briup.cms_jpa.service.IArticleService;
import com.briup.cms_jpa.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService implements IArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public Article findById(Integer id) {
        Article article = articleDao.getOne(id);
        //每点击一次该文章，就点击数量加1
        article.setClickTimes(article.getClickTimes()+1);
        articleDao.save(article);
        return article;
    }

    @Override
    public void saveOrUpdate(Article article) {
        try{
            article.setPublishDate(new Date());
            article.setClickTimes(0);
            articleDao.save(article);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomerException("参数不能为空");
        }
    }
    @Override
    public void deleteById(Integer id) {
        try{
            articleDao.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomerException("该文章不存在，无法删除");
        }
    }
}
