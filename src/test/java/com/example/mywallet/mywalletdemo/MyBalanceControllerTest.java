package com.example.mywallet.mywalletdemo;

import com.example.mywallet.mywalletdemo.controller.MyBalanceController;
import com.example.mywallet.mywalletdemo.model.BalanceHistory;
import com.example.mywallet.mywalletdemo.model.MyBalance;
import com.example.mywallet.mywalletdemo.service.MyBalanceService;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MyBalanceControllerTest {
    @Mock
    MyBalanceService myBalanceService;
    @InjectMocks
    MyBalanceController myBalanceController;
    @Test
    public void creditWalletTest(){

        MyBalance one = new MyBalance(1, 2000.00, 0);

        when(myBalanceService.creditMyWallet(one)).thenReturn(one);

        double creditedBalance = myBalanceController.creditWallet(one);

        assertEquals(2000.00,creditedBalance);
    }

    @Test
    public void debitWalletTest(){

        MyBalance one = new MyBalance(1,0,500.00);

        when(myBalanceService.debitMyWallet(one)).thenReturn(one);

        double debitedBalance = myBalanceController.debitWallet(one);

        assertEquals(500.00,debitedBalance);
    }

    @Test
    public void getDetailsOfAllTest(){

        BalanceHistory one = new BalanceHistory(1,1,2000.00,0.00);
        BalanceHistory two = new BalanceHistory(1,2,5000.00,0.00);
        BalanceHistory three = new BalanceHistory(2,1,0.00,500.00);

        final List<BalanceHistory> list = Arrays.asList(three,two,one);
        when(myBalanceService.getDetailsOfAllUser()).thenReturn(list);
        List<BalanceHistory> balanceList = myBalanceController.getDetailOfAll();
        Java6Assertions.assertThat(balanceList).containsExactly(three,two,one);
    }

    @Test
    public void getCurrentBalanceTest(){

        BalanceHistory one = new BalanceHistory(1,1,2000.00,0.00);
        BalanceHistory two = new BalanceHistory(2,1,5000.00,1000.00);
        BalanceHistory three = new BalanceHistory(3,2,0.00,500.00);

        final List<BalanceHistory> list = Arrays.asList(three,two,one);
        when(myBalanceService.getCurrentBalance(1)).thenReturn(6000.00);
        var currentBalance = myBalanceController.getCurrentBalance(1);
        assertEquals(6000.00,currentBalance);
    }

}


