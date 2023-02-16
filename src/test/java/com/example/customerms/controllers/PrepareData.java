package com.example.customerms.controllers;

import com.example.customerms.model.Customer;
import com.example.customerms.model.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class PrepareData {

    public static CustomerDTO prepareCustomerDTOWithId() {
        return CustomerDTO.builder()
                .customerId(1L)
                .customerName("testName")
                .emailId("test@org.com")
                .build();
    }

    public static Customer prepareCustomerWithoutId() {
        return Customer.builder()
                .customerName("testName")
                .emailId("test@org.com")
                .build();
    }

    public static List<CustomerDTO> prepareCustomerDTOSList() {

        List<CustomerDTO> customerDTOS = new ArrayList<>(3);
        customerDTOS.add(CustomerDTO.builder().customerId(1L).customerName("test1").emailId("email1@test.com").build());
        customerDTOS.add(CustomerDTO.builder().customerId(2L).customerName("test2").emailId("email2@test.com").build());
        customerDTOS.add(CustomerDTO.builder().customerId(3L).customerName("test3").emailId("email3@test.com").build());
        return customerDTOS;
    }

}
