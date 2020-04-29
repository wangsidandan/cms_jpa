package com.briup.cms_jpa.service.impl;

import com.briup.cms_jpa.bean.Category;
import com.briup.cms_jpa.dao.CategoryDao;
import com.briup.cms_jpa.exception.CustomerException;
import com.briup.cms_jpa.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryDao cateGoryDao;
    @Override
    public List<Category> findAll() {
        return cateGoryDao.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return cateGoryDao.getOne(id);
    }

    @Override
    public void saveOrUpdate(Category category) {
        try{
            cateGoryDao.save(category);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomerException("参数不能为空");
        }
    }

    @Override
    public void deleteById(Integer id) {
        try{
            cateGoryDao.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomerException("该目录不存在，无法删除");
        }

    }
}
