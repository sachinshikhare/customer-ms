package com.example.customerms.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@Data
@EqualsAndHashCode
@ToString
public class Customer {

    private Long customerId;

    private String customerName;

    private String emailId;
}
