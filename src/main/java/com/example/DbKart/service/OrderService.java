package com.example.DbKart.service;


import com.example.DbKart.dto.request.ItemRequest;
import com.example.DbKart.dto.request.OrderRequest;
import com.example.DbKart.dto.response.OrderResponse;
import com.example.DbKart.exception.CustomerNotFoundException;
import com.example.DbKart.exception.ProductNotAvailableException;
import com.example.DbKart.modal.Customer;
import com.example.DbKart.modal.Enum.ProductStatus;
import com.example.DbKart.modal.OrderEntity;
import com.example.DbKart.modal.Product;
import com.example.DbKart.repo.CustomerRepository;
import com.example.DbKart.repo.OrderRepository;
import com.example.DbKart.repo.ProductRepository;
import com.example.DbKart.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Optional<Customer> customerOptional = customerRepository.findByEmail(orderRequest.getCustomerEmail());
        if(customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Email is not valid");
        }

        Customer customer = customerOptional.get();

        List<Product> productsRequested = new ArrayList<>();
        int totalValue = 0;

        for(ItemRequest itemRequest: orderRequest.getItemRequests()) {
            Optional<Product> optionalProduct = productRepository.findById(itemRequest.getId());

            if(optionalProduct.isEmpty()) {
                throw new ProductNotAvailableException("Sorry, the product id=" + itemRequest.getId()+" is invalid");
            }
            Product product = optionalProduct.get();
            if(product.getQuantity() < itemRequest.getRequiredQuantity()) {
                throw new ProductNotAvailableException("Sorry, the product with id = "+product.getId()+" is out of stock");
            }

            product.setQuantity(product.getQuantity()-itemRequest.getRequiredQuantity());
            if(product.getQuantity()==0) {
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            totalValue += itemRequest.getRequiredQuantity()*product.getPrice();
            productsRequested.add(product);
        }

        // complete the order entity and save it
        OrderEntity order = OrderTransformer.orderRequestToOrderEntity(totalValue);
        order.setCustomer(customer);
        order.setProducts(productsRequested);
        OrderEntity savedOrder = orderRepository.save(order);

        // complete product and customer entity and save them as well
        for(Product product: productsRequested) {
            product.getOrders().add(savedOrder);
        }

        customer.getOrders().add(savedOrder);
        //save customer and product
        customerRepository.save(customer); // customer + order;
        productRepository.saveAll(productsRequested);

        //send email

        sendEmail(savedOrder);

        // return the response;
        return OrderTransformer.orderToOrderResponse(savedOrder);
    }

    private void sendEmail(OrderEntity savedOrder) {
        String text="Hi "+ savedOrder.getCustomer().getName()+"Your order is placed";
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("diptanubaidyajava@gmail.com");
        message.setTo(savedOrder.getCustomer().getEmail());
        message.setSubject("order place");
        message.setText(text);

        javaMailSender.send(message);
    }
}