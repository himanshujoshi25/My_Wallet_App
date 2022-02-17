package com.example.mywallet.mywalletdemo.service;

import com.example.mywallet.mywalletdemo.exception.CustomerNotFoundException;
import com.example.mywallet.mywalletdemo.exception.InsufficientBalanceException;
import com.example.mywallet.mywalletdemo.model.BalanceHistory;
import com.example.mywallet.mywalletdemo.model.Customer;
import com.example.mywallet.mywalletdemo.model.MyBalance;
import com.example.mywallet.mywalletdemo.repository.BalanceHistoryRepository;
import com.example.mywallet.mywalletdemo.repository.CustomerRepository;
import com.example.mywallet.mywalletdemo.repository.MyBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class MyBalanceService {
    @Autowired
    MyBalanceRepository myBalanceRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BalanceHistoryRepository balanceHistoryRepository;

    public Object getCurrentBalance(int id){
        AtomicReference<Double> currentBalance = new AtomicReference<>((double) 0);
        List<BalanceHistory> list = balanceHistoryRepository.findAllByCustomerBId(id);

        list.stream()
                .collect(Collectors.groupingBy(item->item.getCustomerBId(),
                        Collectors.summingDouble(item->item.getCreditBalance()-item.getDebitBalance())))
                .forEach((customerBId,sumVal)-> currentBalance.set(Double.valueOf(sumVal)));

        return currentBalance;
    }

    public MyBalance creditMyWallet(MyBalance myBalance){
        Customer id = customerRepository.findByCustomerId(myBalance.getCustomerBId());
        if(id == null){
            throw new CustomerNotFoundException("Entered customer id does not exists ");
        }

        BalanceHistory balances = new BalanceHistory();
        balances.setCustomerBId(myBalance.getCustomerBId());
        balances.setCreditBalance(myBalance.getCreditBalance());
        balances.setLastUpdate();
        balanceHistoryRepository.save(balances);
        return myBalanceRepository.save(myBalance);
    }

    public MyBalance debitMyWallet(MyBalance myBalance){
        Customer id = customerRepository.findByCustomerId(myBalance.getCustomerBId());
        if(id == null){
            throw new CustomerNotFoundException("Entered customer id does not exists ");
        }
        BalanceHistory balances = new BalanceHistory();
        balances.setCustomerBId(myBalance.getCustomerBId());
        balances.setDebitBalance(myBalance.getDebitBalance());
        Double currentBalance = Double.valueOf(String.valueOf(getCurrentBalance(balances.getCustomerBId())));
        if(currentBalance < myBalance.getDebitBalance())
            throw new InsufficientBalanceException("Insufficient Balance can't process request");
        balances.setLastUpdate();
        balanceHistoryRepository.save(balances);
        return myBalanceRepository.save(myBalance);
    }

    public List<BalanceHistory> getDetailsOfAllUser(){
        List<BalanceHistory> balances = new ArrayList<>();
        balanceHistoryRepository.findAll().forEach(balances1 -> balances.add(balances1));
        return balances;
    }

    public List<BalanceHistory> getDetailsByCustomerId(int id){
        return balanceHistoryRepository.findAllByCustomerBId(id);
    }

}