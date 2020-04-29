package com.briup.cms_jpa.service;

import com.briup.cms_jpa.bean.Category;
import com.briup.cms_jpa.bean.ex.CategoryVM;

import java.util.List;

public interface ICategoryService {
    List<CategoryVM> findAll();
    CategoryVM findById(Integer id);
    void saveOrUpdate(Category category);
    void deleteById(Integer id);
}
