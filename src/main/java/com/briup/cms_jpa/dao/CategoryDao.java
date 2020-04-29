package com.briup.cms_jpa.dao;

import com.briup.cms_jpa.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {

}
