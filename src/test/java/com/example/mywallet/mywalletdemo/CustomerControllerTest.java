package com.example.mywallet.mywalletdemo;

import com.example.mywallet.mywalletdemo.controller.CustomerController;
import com.example.mywallet.mywalletdemo.model.Customer;
import com.example.mywallet.mywalletdemo.service.CustomerService;
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
public class CustomerControllerTest {
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;
    @Test
    public void getAllCustomersTest(){

        Customer customerOne = new Customer(1, "Rick", "Johnson",
                "rick@gmail.com", 123456789, "Australia");

        Customer customerTwo = new Customer(2, "Morty", "Smith",
                "morty@yahoo.com", 1142532298, "Canada");

        Customer customerThree = new Customer(3, "Sam", "Martin",
                "sam@gmail.com", 1152837289, "Paris");

        final List<Customer> list = Arrays.asList(customerThree,customerTwo,customerOne);

        when(customerService.getAllCustomers()).thenReturn(list);

        List<Customer> customerList = customerController.getAllCustomers();
        Java6Assertions.assertThat(customerList).containsExactly(customerThree,customerTwo,customerOne);
    }
    @Test
    public void registerUserTest() {

        Customer user = new Customer(1, "Rick", "Johnson",
                "rick@gmail.com", 123456789, "Australia");

        when(customerService.registerNewUser(user)).thenReturn(user);

        int customerId = customerController.registerUser(user);

        assertEquals(1,customerId);
    }

    @Test
    public void getRegisteredCustomerByIdTest(){

        when(customerService.getRegisteredCustomer(1)).thenReturn(new Customer(1,
                "Rick", "Johnson", "rick@gmail.com",
                123456789, "Australia"));

        Customer customerList = customerController.getRegisteredCustomerById(1);

        assertEquals("rick@gmail.com",customerList.getCustomerMail());
    }
}
