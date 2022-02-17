package com.example.mywallet.mywalletdemo.controller;

import com.example.mywallet.mywalletdemo.exception.CustomerNotFoundException;
import com.example.mywallet.mywalletdemo.model.Customer;
import com.example.mywallet.mywalletdemo.repository.CustomerRepository;
import com.example.mywallet.mywalletdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@ResponseBody
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/user/allUsers")
    public List<Customer> getAllCustomers()  {
        return customerService.getAllCustomers();
    }

    @GetMapping("/user/details/{id}")
    public Customer getRegisteredCustomerById(@PathVariable("id") int id){
        Customer customer = customerService.getRegisteredCustomer(id);
        if(customer == null)
            throw new CustomerNotFoundException("Customer with id: "+id + " not exists");
        return customer;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/registerUser")
    public int registerUser(@RequestBody Customer customer){
        Customer userCreated = customerService.registerNewUser(customer);

        return userCreated.getCustomerId();
    }

    @PutMapping("/user/setPassword")
    public void setPassword(@RequestBody Customer customer){
        customerService.setPassword(customer);
    }

}