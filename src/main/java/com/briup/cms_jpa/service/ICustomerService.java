package com.briup.cms_jpa.service;

import com.briup.cms_jpa.bean.Customer;
import com.briup.cms_jpa.dao.CustomerDao;

import java.util.List;

public interface ICustomerService{
    List<Customer> findAll();
    void saveOrUpdate(Customer customer);
    void deleteById(Integer id);
    Customer findById(Integer id);
}
