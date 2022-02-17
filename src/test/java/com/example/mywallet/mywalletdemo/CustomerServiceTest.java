package com.example.mywallet.mywalletdemo;

import com.example.mywallet.mywalletdemo.model.Customer;
import com.example.mywallet.mywalletdemo.repository.CustomerRepository;
import com.example.mywallet.mywalletdemo.service.CustomerService;
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
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerService customerService;
    @Test
    public void getRegisteredCustomerTest(){

        when(customerRepository.findByCustomerId(1)).thenReturn(new Customer(1, "Rick",
                "Johnson", "rick@gmail.com",
                123456789, "Australia"));

        Customer customerList = customerService.getRegisteredCustomer(1);

        assertEquals("rick@gmail.com",customerList.getCustomerMail());
    }

    @Test
    public void registerUserTest() {

        Customer user = new Customer(1, "Rick", "Johnson",
                "rick@gmail.com", 123456789, "Australia");
        when(customerRepository.save(user)).thenReturn(user);

        Customer customerList = customerService.registerNewUser(user);

        assertEquals(user,customerList);
    }

    @Test
    public void getAllCustomerTest(){

        Customer customerOne = new Customer(1, "Rick", "Johnson",
                "rick@gmail.com", 123456789, "Australia");

        Customer customerTwo = new Customer(2, "Morty", "Smith",
                "morty@yahoo.com", 1142532298, "Canada");

        Customer customerThree = new Customer(3, "Sam", "Martin",
                "sam@gmail.com", 1152837289, "Paris");

        final List<Customer> list = Arrays.asList(customerOne,customerTwo,customerThree);

        when(customerRepository.findAll()).thenReturn(list);

        List<Customer> customerList = customerService.getAllCustomers();
        assertEquals(3,customerList.size());
    }

}
