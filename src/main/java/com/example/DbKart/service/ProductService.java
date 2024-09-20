package com.example.DbKart.service;


import com.example.DbKart.dto.request.ProductRequest;
import com.example.DbKart.dto.response.ProductResponse;
import com.example.DbKart.exception.SellerNotFoundException;
import com.example.DbKart.modal.Product;
import com.example.DbKart.modal.Seller;
import com.example.DbKart.repo.SellerRepository;
import com.example.DbKart.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    public ProductResponse addProduct(ProductRequest productRequest,
                                      String sellerEmail) {

        Optional<Seller> sellerOptional = sellerRepository.findByEmail(sellerEmail);
        if(sellerOptional.isEmpty()) {
            throw new SellerNotFoundException("Invalid seller email");
        }

        Seller seller = sellerOptional.get();

        Product product = ProductTransformer.productRequestToProduct(productRequest);
        product.setSeller(seller);

        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller); // save both seller and product
        int size = seller.getProducts().size();
        Product savedProduct = savedSeller.getProducts().get(size - 1);

        return ProductTransformer.productToProductResponse(savedProduct);
    }
}