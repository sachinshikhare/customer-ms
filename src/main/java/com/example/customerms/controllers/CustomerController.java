package com.example.customerms.controllers;

import com.example.customerms.mappers.Mapper;
import com.example.customerms.model.Customer;
import com.example.customerms.model.CustomerDTO;
import com.example.customerms.service.CustomerService;
import com.example.customerms.validators.RequestValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private RequestValidators requestValidators;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerRequest) {
        CustomerDTO customerDTO = mapper.mapCustomerToCustomerDTO(customerRequest);
        requestValidators.validateRequest(customerDTO);
        CustomerDTO createdCustomerDTO = customerService.createCustomer(customerDTO);
        Customer customerResponse = mapper.mapCustomerDTOToCustomer(createdCustomerDTO);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        List<Customer> customersResponse = customerDTOS.stream()
                .map(customerDTO -> mapper.mapCustomerDTOToCustomer(customerDTO))
                .collect(Collectors.toList());
        return new ResponseEntity<>(customersResponse, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        requestValidators.validateInputCustomerId(customerId);
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        Customer customerResponse = mapper.mapCustomerDTOToCustomer(customerDTO);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long customerId, @RequestBody Customer customer) {
        CustomerDTO customerDTO = mapper.mapCustomerToCustomerDTO(customer);
        requestValidators.validateRequest(customerDTO, customerId);
        CustomerDTO updatedCustomerDTO = customerService.updateCustomerById(customerId, customerDTO);
        Customer customerResponse = mapper.mapCustomerDTOToCustomer(updatedCustomerDTO);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long customerId) {
        requestValidators.validateInputCustomerId(customerId);
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
