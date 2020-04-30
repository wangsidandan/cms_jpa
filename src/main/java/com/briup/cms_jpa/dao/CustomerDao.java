package com.briup.cms_jpa.dao;

import com.briup.cms_jpa.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

    Customer findByUsername(String s);
}
