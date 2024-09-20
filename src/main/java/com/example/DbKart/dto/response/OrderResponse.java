package com.example.DbKart.dto.response;

import com.example.DbKart.modal.Enum.OrderStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {

    String orderNo;  // UUID

    double totalValue;

    OrderStatus orderStatus;

    Date orderedAt;

    CustomerResponse customer;

    List<ProductResponse> products;

}
