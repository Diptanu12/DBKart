package com.example.DbKart.repo;

import com.example.DbKart.modal.Customer;
import com.example.DbKart.modal.Enum.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByAge(String age);

//    @Query(value = "select * from customer where gender = :g AND age >= :age",nativeQuery = true)
//    List<Customer> getCustomersByGenderAndAgeGreaterThan(String g, int age);

    @Query(value = "select c from Customer c where c.gender = :g AND c.age >= :age")
    List<Customer> getCustomersByGenderAndAgeGreaterThan(Gender g, int age);

    Optional<Customer> findByEmail(String email);
}