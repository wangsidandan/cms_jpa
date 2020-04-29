package com.briup.cms_jpa.service;

import com.briup.cms_jpa.bean.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    void saveOrUpdate(Category category);
    void deleteById(Integer id);
}
