package com.example.DbKart.transformer;


import com.example.DbKart.dto.request.ProductRequest;
import com.example.DbKart.dto.response.ProductResponse;
import com.example.DbKart.modal.Enum.ProductStatus;
import com.example.DbKart.modal.Product;

public class ProductTransformer {

    public static Product productRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .quantity(productRequest.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .seller(SellerTransformer.sellerToSellerResponse(product.getSeller()))
                .build();
    }
}