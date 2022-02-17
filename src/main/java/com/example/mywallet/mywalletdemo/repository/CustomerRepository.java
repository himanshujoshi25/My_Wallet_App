package com.example.mywallet.mywalletdemo.repository;

import com.example.mywallet.mywalletdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByCustomerMail(String customerMail);
    Customer findByCustomerId(int customerId);
    Customer findByCustomerNum(int customerNum);
}