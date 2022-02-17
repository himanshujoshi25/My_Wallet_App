package com.example.mywallet.mywalletdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "BalanceHistory")
public class BalanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customerBId;
    private double creditBalance=0;
    private double debitBalance=0;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customerId")
    private Customer customer;

    public BalanceHistory(int id, int customerBId, double creditBalance, double debitBalance) {
        this.id = id;
        this.customerBId = customerBId;
        this.creditBalance = creditBalance;
        this.debitBalance = debitBalance;
    }

    public BalanceHistory() {


    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @PrePersist
    public void setLastUpdate(){
        lastUpdate = new Date();
    }
    public Date getLastUpdate(){
        return lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerBId() {
        return customerBId;
    }

    public void setCustomerBId(int customerBId) {
        this.customerBId = customerBId;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public double getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(double debitBalance) {
        this.debitBalance = debitBalance;
    }
}