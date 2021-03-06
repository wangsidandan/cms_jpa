package com.briup.cms_jpa.service.impl;

import com.briup.cms_jpa.bean.Category;
import com.briup.cms_jpa.bean.ex.CategoryVM;
import com.briup.cms_jpa.dao.CategoryDao;
import com.briup.cms_jpa.dao.ex.CategoryExDao;
import com.briup.cms_jpa.exception.CustomerException;
import com.briup.cms_jpa.service.ICategoryService;
import com.briup.cms_jpa.util.UpdateSelectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryDao cateGoryDao;
    @Autowired
    private CategoryExDao categoryExDao;
    @Override
    public List<CategoryVM> findAll() {
        return categoryExDao.findAll();
    }

    @Override
    public CategoryVM findById(Integer id) {
        return categoryExDao.getOne(id);
    }

    @Override
    public void saveOrUpdate(Category category) {
        Category target=null;
        try{
            if(category.getId()!=null){
                 target = cateGoryDao.getOne(category.getId());
            }else{
                CategoryVM  categoryVM = categoryExDao.findByName(category.getName());
                if (categoryVM!=null){
                    throw new CustomerException("栏目已经存在");
                }
                target=category;
            }
            BeanUtils.copyProperties(category,target,UpdateSelectUtil.getNullPropertyNames(category));
            cateGoryDao.save(target);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomerException("参数不能为空");
        }
    }

    @Override
    public void deleteById(Integer id) {
        try{
            categoryExDao.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomerException("该目录不存在，无法删除");
        }
    }
}
