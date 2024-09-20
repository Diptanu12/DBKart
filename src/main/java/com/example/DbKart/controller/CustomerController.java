package com.example.DbKart.controller;


import com.example.DbKart.dto.request.CustomerRequest;
import com.example.DbKart.dto.response.CustomerResponse;
import com.example.DbKart.modal.Customer;
import com.example.DbKart.modal.Enum.Gender;
import com.example.DbKart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity findByAge(@PathVariable("age") String age) {
       List<CustomerResponse> customerResponses=  customerService.findByAge(age);
        return new  ResponseEntity<>(customerResponses, HttpStatus.ACCEPTED);

    }

    @GetMapping("/gender/{gender}/age/{age}")
    public List<CustomerResponse> findByGenderAndAgeGreaterThan(@PathVariable Gender gender,
                                                                @PathVariable int age) {
        return customerService.findByGenderAndAgeGreaterThan(gender, age);
    }
}
