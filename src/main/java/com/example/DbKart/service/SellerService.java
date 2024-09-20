package com.example.DbKart.service;


import com.example.DbKart.dto.request.SellerRequest;
import com.example.DbKart.dto.response.SellerResponse;
import com.example.DbKart.modal.Seller;
import com.example.DbKart.repo.SellerRepository;
import com.example.DbKart.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;


    public SellerResponse addSeller(SellerRequest sellerRequest) {
        Seller seller = SellerTransformer.sellerRequestToSeller(sellerRequest);
        Seller savedSeller = sellerRepository.save(seller);
        return SellerTransformer.sellerToSellerResponse(savedSeller);
    }
}