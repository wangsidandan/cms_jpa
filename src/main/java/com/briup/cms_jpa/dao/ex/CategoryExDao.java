package com.briup.cms_jpa.dao.ex;

import com.briup.cms_jpa.bean.Article;
import com.briup.cms_jpa.bean.ex.CategoryVM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryExDao extends JpaRepository<CategoryVM,Integer> {
}
