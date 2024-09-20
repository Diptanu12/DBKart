package com.example.DbKart.service;

import com.example.DbKart.dto.request.CustomerRequest;
import com.example.DbKart.dto.response.CustomerResponse;
import com.example.DbKart.modal.Customer;
import com.example.DbKart.modal.Enum.Gender;
import com.example.DbKart.repo.CustomerRepository;
import com.example.DbKart.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }
    public List<CustomerResponse> findByAge(String age){
       List<Customer> customers= customerRepository.findByAge(age );
       List<CustomerResponse> customerResponses=new ArrayList<>();

       for(Customer customer:customers){
           customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
       }
       return customerResponses;
    }
    public List<CustomerResponse> findByGenderAndAgeGreaterThan(Gender gender, int age) {
        List<Customer> customers = customerRepository.getCustomersByGenderAndAgeGreaterThan(gender,age);

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer: customers) {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }

        return customerResponses;
    }
}
