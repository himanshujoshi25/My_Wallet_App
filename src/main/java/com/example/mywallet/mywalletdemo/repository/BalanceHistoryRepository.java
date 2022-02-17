package com.example.mywallet.mywalletdemo.repository;
import com.example.mywallet.mywalletdemo.model.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory,Integer> {
    List<BalanceHistory> findAllByCustomerBId(int customerBId);

}