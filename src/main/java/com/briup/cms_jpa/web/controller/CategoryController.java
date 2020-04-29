package com.briup.cms_jpa.web.controller;

import com.briup.cms_jpa.bean.Category;
import com.briup.cms_jpa.service.ICategoryService;
import com.briup.cms_jpa.util.Message;
import com.briup.cms_jpa.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "目录相关接口")
public class CategoryController {
    @Autowired
    private ICategoryService service;
    @GetMapping("category/findAll")
    @ApiOperation("查询所有目录")
    public Message<List<Category>> findAll(){
        return MessageUtil.success(service.findAll(),"查找成功");
    }
    @ApiOperation("根据id查找目录")
    @GetMapping("category/findById")
    @ApiImplicitParam(name = "id",value = "客户id",paramType = "query",dataType = "int",required = true)
    public Message<Category>findById(Integer id){
        return MessageUtil.success(service.findById(id),"查询成功");
    }
    @ApiOperation("添加或更新目录")
    @PostMapping("category/saveOrUpdate")
    public Message saveOrUpdate(Category category){
        String message="";
        if (category.getId()==null){
            message="添加成功";
        }else {
            message="更新成功";
        }
        service.saveOrUpdate(category);
        return MessageUtil.success(message);
    }
    @GetMapping("category/deleteById")
    @ApiOperation("根据id删除目录")
    @ApiImplicitParam(name = "id",value = "客户id",paramType = "query",dataType = "int",required = true)
    public Message deleteById(Integer id){
        service.deleteById(id);
        return MessageUtil.success("删除成功");
    }
}
