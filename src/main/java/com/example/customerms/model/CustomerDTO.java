package com.example.customerms.model;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Builder
public class CustomerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "email_id")
    private String emailId;

    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, String customerName, String emailId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.emailId = emailId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
