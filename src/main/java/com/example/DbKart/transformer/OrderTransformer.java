package com.example.DbKart.transformer;

import com.example.DbKart.dto.response.OrderResponse;
import com.example.DbKart.dto.response.ProductResponse;
import com.example.DbKart.modal.Enum.OrderStatus;
import com.example.DbKart.modal.OrderEntity;
import com.example.DbKart.modal.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static OrderEntity orderRequestToOrderEntity(int totalValue) {
        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .totalValue(totalValue)
                .orderStatus(OrderStatus.PLACED)
                .build();
    }

    public static OrderResponse orderToOrderResponse(OrderEntity order) {
        OrderResponse orderResponse = OrderResponse.builder()
                .orderNo(order.getOrderNo())
                .totalValue(order.getTotalValue())
                .orderStatus(order.getOrderStatus())
                .orderedAt(order.getOrderedAt())
                .customer(CustomerTransformer.customerToCustomerResponse(order.getCustomer()))
                .build();

        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product: order.getProducts()) {
            productResponses.add(ProductTransformer.productToProductResponse(product));
        }

        orderResponse.setProducts(productResponses);
        return orderResponse;
    }
}