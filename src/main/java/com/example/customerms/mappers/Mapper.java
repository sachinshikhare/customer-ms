package com.example.customerms.mappers;

import com.example.customerms.model.Customer;
import com.example.customerms.model.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        if (customer != null) {
            return CustomerDTO.builder()
                    .customerId(customer.getCustomerId())
                    .customerName(customer.getCustomerName())
                    .emailId(customer.getEmailId())
                    .build();
        }
        return null;
    }

    public Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
        if (customerDTO != null) {
            return Customer.builder()
                    .customerId(customerDTO.getCustomerId())
                    .customerName(customerDTO.getCustomerName())
                    .emailId(customerDTO.getEmailId())
                    .build();
        }
        return null;
    }
}
