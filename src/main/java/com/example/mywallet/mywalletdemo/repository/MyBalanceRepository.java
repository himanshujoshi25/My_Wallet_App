package com.example.mywallet.mywalletdemo.repository;

import com.example.mywallet.mywalletdemo.model.MyBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBalanceRepository extends JpaRepository<MyBalance,Integer> {

}