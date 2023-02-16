package com.example.customerms.service;

import com.example.customerms.exceptions.ResourceNotFoundException;
import com.example.customerms.model.CustomerDTO;
import com.example.customerms.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return customerRepository.save(customerDTO);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDTO getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer wth id %s not found", customerId)));
    }

    public boolean isCustomerExist(Long customerId) {
        return customerRepository.existsById(customerId);
    }

    public CustomerDTO updateCustomerById(Long customerId, CustomerDTO customerDTO) {
        if (isCustomerExist(customerId)) {
            return customerRepository.save(customerDTO);
        }
        throw new EntityNotFoundException(String.format("Customer with id %s not found", customerId));
    }

    public void deleteCustomerById(Long customerId) {
        if (isCustomerExist(customerId)) {
            customerRepository.deleteById(customerId);
            return;
        }
        throw new EntityNotFoundException(String.format("Customer with id %s not found", customerId));
    }
}
