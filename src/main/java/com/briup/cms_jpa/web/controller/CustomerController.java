package com.briup.cms_jpa.web.controller;

import com.briup.cms_jpa.bean.Customer;
import com.briup.cms_jpa.service.ICustomerService;
import com.briup.cms_jpa.util.Message;
import com.briup.cms_jpa.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Api(description = "客户相关接口")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @ApiOperation("查询所有用户")
    @GetMapping("customer/findAll")
    public Message<List<Customer>> findAll(){
        customerService.findAll();
        return MessageUtil.success(customerService.findAll(),"查询成功");
    }
    @ApiOperation("添加/更新客户，根据有无id判断")
    //对象描述信息，需要在类上进行
    @PostMapping("customer/saveOrUpdate")
    public Message saveOrUpdate(Customer customer){
        //注意：此处没有办法给customer判空，一定有customer对象，只是属性为null
        String message="";
        if(customer.getId()==null){
            message="添加成功";
        }else {
            message="更新成功";
        }
        customerService.saveOrUpdate(customer);
        return MessageUtil.success(message);
    }
    @ApiOperation("根据客户id删除")
    @ApiImplicitParam(name ="id",value = "客户id",paramType = "query",dataType = "int")
    @GetMapping("customer/deleteById")
    public Message deleteById(Integer id){
        customerService.deleteById(id);
        return MessageUtil.success("删除成功");
    }
    @ApiOperation("根据客户id查询")
    //paramType默认为json格式
    @ApiImplicitParam(name = "id",value = "客户id",paramType = "query",dataType = "int",required = true)
    @GetMapping("customer/findById")
    public Message<Customer> findById(Integer id){
        return MessageUtil.success(customerService.findById(id),"查询成功");
    }
}
