package com.example.DbKart.dto.response;


import com.example.DbKart.modal.Enum.Category;
import com.example.DbKart.modal.Enum.ProductStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    String productName;

    int price;

    Category category;

    int quantity;

    ProductStatus productStatus;

    SellerResponse seller;
}