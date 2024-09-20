package com.example.DbKart.transformer;

import com.example.DbKart.dto.request.CustomerRequest;
import com.example.DbKart.dto.response.CustomerResponse;
import com.example.DbKart.modal.Customer;

public class CustomerTransformer {
    public static Customer customerRequestToCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .age(String.valueOf(customerRequest.getAge()))
                .email(customerRequest.getEmail())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
