package com.briup.cms_jpa.service;

import com.briup.cms_jpa.bean.Article;
import com.briup.cms_jpa.bean.Customer;

import java.util.List;

public interface IArticleService {
    List<Article> findAll();
    void saveOrUpdate(Article article);
    void deleteById(Integer id);
    Article findById(Integer id);
}
