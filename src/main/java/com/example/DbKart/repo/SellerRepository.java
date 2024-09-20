package com.example.DbKart.repo;


import com.example.DbKart.modal.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Optional<Seller> findByEmail(String email);
}
