package com.briup.cms_jpa.dao;

import com.briup.cms_jpa.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

}
