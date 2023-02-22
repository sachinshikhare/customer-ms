package com.example.customerms.controllers;

import com.example.customerms.exceptions.BadRequestException;
import com.example.customerms.exceptions.ResourceNotFoundException;
import com.example.customerms.model.Customer;
import com.example.customerms.model.CustomerDTO;
import com.example.customerms.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void createCustomer_ValidRequest() {
        Customer customer = PrepareData.prepareCustomerWithoutId();
        when(customerRepository.save(any(CustomerDTO.class))).thenReturn(PrepareData.prepareCustomerDTOWithId());
        ResponseEntity<Customer> response = customerController.createCustomer(customer);
        Customer customerResponse = response.getBody();
        assertNotNull(customerResponse);
        assertEquals(customer.getCustomerName(), customerResponse.getCustomerName());
    }

    @Test
    void createCustomer_NullInput() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> customerController.createCustomer(null));
        assertEquals("Invalid request", exception.getMessage());
    }

    @Test
    void createCustomer_InvalidEmailId() {
        Customer customer = PrepareData.prepareCustomerWithoutId();
        customer.setEmailId("");
        BadRequestException exception = assertThrows(BadRequestException.class, () -> customerController.createCustomer(customer));
        assertEquals("Invalid Customer email id", exception.getMessage());
    }



    @Test
    void getAllCustomers() {

        when(customerRepository.findAll()).thenReturn(PrepareData.prepareCustomerDTOSList());
        List<Customer> customers = customerController.getAllCustomers().getBody();
        assertNotNull(customers);
        assertEquals(3, customers.size());
        assertEquals("email2@test.com", customers.get(1).getEmailId());
    }


    @Test
    void getCustomerById_DataPresent() {

        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(PrepareData.prepareCustomerDTOWithId()));
        Customer customer = customerController.getCustomerById(1L).getBody();
        assertNotNull(customer);
        assertEquals(1, customer.getCustomerId());
        assertEquals("testName", customer.getCustomerName());
    }

    @Test
    void getCustomerById_InvalidInput() {

        BadRequestException exception = assertThrows(BadRequestException.class, () -> customerController.getCustomerById(0L));
        assertEquals("Invalid Customer id", exception.getMessage());
    }

    @Test
    void getCustomerById_DataNotPresent() {

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomerById(1L));
        assertEquals("Customer wth id 1 not found", exception.getMessage());
    }

    @Test
    void updateCustomerById() {
        when(customerRepository.existsById(any())).thenReturn(true);
        when(customerRepository.save(any(CustomerDTO.class))).thenReturn(PrepareData.prepareCustomerDTOWithId());
        Customer updatedCustomer = customerController.updateCustomerById(1L, Customer.builder().customerName("NameToBeUpdated").emailId("test@org.com").build()).getBody();
        assertNotNull(updatedCustomer);
        assertNotEquals("NameToBeUpdated", updatedCustomer.getCustomerName());
        assertEquals("test@org.com", updatedCustomer.getEmailId());
    }

    @Test
    void deleteCustomerById() {
        when(customerRepository.existsById(any())).thenReturn(true);
        customerController.deleteCustomerById(1L);
        verify(customerRepository, times(1)).deleteById(any());
    }
}