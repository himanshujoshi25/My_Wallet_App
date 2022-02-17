package com.example.mywallet.mywalletdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MyBalance")
public class MyBalance {

    @Id
    private int customerBId;
    private double creditBalance=0;
    private double debitBalance=0;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "myBalance")
    private Customer customer;

    public MyBalance(int customerBId, double creditBalance, double debitBalance) {
        this.customerBId = customerBId;
        this.creditBalance = creditBalance;
        this.debitBalance = debitBalance;
    }

    public MyBalance() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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