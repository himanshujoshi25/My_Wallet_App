package com.example.mywallet.mywalletdemo.controller;

import com.example.mywallet.mywalletdemo.exception.CustomerNotFoundException;
import com.example.mywallet.mywalletdemo.model.BalanceHistory;
import com.example.mywallet.mywalletdemo.model.MyBalance;
import com.example.mywallet.mywalletdemo.service.CustomerService;
import com.example.mywallet.mywalletdemo.service.MyBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyBalanceController {
    @Autowired
    MyBalanceService myBalanceService;
    @Autowired
    CustomerService customerService;

    @GetMapping("user/myBalance/myHistory/{customerBId}")
    public List<BalanceHistory> getDetailsByCustomerId(@PathVariable("customerBId") int customerBId){
        List<BalanceHistory> balanceHistory = myBalanceService.getDetailsByCustomerId(customerBId);
        if(balanceHistory == null)
            throw new CustomerNotFoundException("Customer with id: "+ customerBId + " does not exists");
        return balanceHistory;
    }

    @GetMapping("user/myBalance/currentBalance/{customerBId}")
    public Object getCurrentBalance(@PathVariable("customerBId") int customerBId){
        Object obj = myBalanceService.getCurrentBalance(customerBId);
        if(obj == null)
            throw new CustomerNotFoundException("Customer with id: "+ customerBId + " does not exists");
        return obj;
    }

    @GetMapping("user/allUsers/myBalance/details")
    public List<BalanceHistory> getDetailOfAll(){
        return myBalanceService.getDetailsOfAllUser();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/user/myBalance/credit")
    public Double creditWallet(@RequestBody MyBalance myBalance){
        myBalanceService.creditMyWallet(myBalance);
        return myBalance.getCreditBalance();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/user/myBalance/debit")
    public Double debitWallet(@RequestBody MyBalance myBalance){
        myBalanceService.debitMyWallet(myBalance);
        return myBalance.getDebitBalance();
    }

}