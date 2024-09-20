package com.example.DbKart.dto.request;


import com.example.DbKart.modal.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {

    String productName;

    int price;

    Category category;

    int quantity;
}
