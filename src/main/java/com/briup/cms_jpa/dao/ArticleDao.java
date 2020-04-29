package com.briup.cms_jpa.dao;

import com.briup.cms_jpa.bean.Article;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article,Integer> {
}
