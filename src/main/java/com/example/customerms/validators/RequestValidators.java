package com.example.customerms.validators;

import com.example.customerms.exceptions.BadRequestException;
import com.example.customerms.model.CustomerDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RequestValidators {

    public void validateRequest(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new BadRequestException("Invalid request");
        }
        if (!StringUtils.hasText(customerDTO.getCustomerName())) {
            throw new BadRequestException("Invalid Customer name");
        }
        if (!StringUtils.hasText(customerDTO.getEmailId())) {
            throw new BadRequestException("Invalid Customer email id");
        }
    }

    public void validateInputCustomerId(Long customerId) {
        if (customerId <= 0) {
            throw new BadRequestException("Invalid Customer id");
        }
    }

    public void validateRequest(CustomerDTO customerDTO, Long customerId) {
        validateRequest(customerDTO);
        validateInputCustomerId(customerId);
    }
}
