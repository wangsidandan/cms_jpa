package com.briup.cms_jpa.service.impl;

import com.briup.cms_jpa.bean.Customer;
import com.briup.cms_jpa.dao.CustomerDao;
import com.briup.cms_jpa.exception.CustomerException;
import com.briup.cms_jpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveOrUpdate(Customer customer) throws CustomerException{
        //数据库已经设置了关键字段不能为空的前提下
            //DataIntegrityViolationException
        try{
            customerDao.save(customer);
        }catch (Exception e){
           throw new CustomerException("参数不能为空");
        }
    }

    @Override
    public void deleteById(Integer id) throws CustomerException{
        //如果id找不到会报错有默认处理错误    EmptyResultDataAccessException
        try {
            customerDao.deleteById(id);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new CustomerException("该用户不存在，无法删除");
        }
    }

    @Override
    public Customer findById(Integer id) {
        return customerDao.getOne(id);
    }

    /*
    * 登陆验证
    */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerDao.findByUsername(s);
        if(customer==null){
            throw new UsernameNotFoundException("密码有误");
        }
        return new User(customer.getUsername(),passwordEncoder.encode(customer.getPassword()), AuthorityUtils.createAuthorityList("admin"));
    }
}
