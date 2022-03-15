package com.zware.ecommercebackend.service.impl;

import com.zware.ecommercebackend.dao.CustomerRepository;
import com.zware.ecommercebackend.dto.Purchase;
import com.zware.ecommercebackend.dto.PurchaseResponse;
import com.zware.ecommercebackend.entity.Address;
import com.zware.ecommercebackend.entity.Customer;
import com.zware.ecommercebackend.entity.Order;
import com.zware.ecommercebackend.entity.OrderItem;
import com.zware.ecommercebackend.service.CheckoutService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Address shippingAddress = purchase.getShippingAddress();
        Address billingAddress = purchase.getBillingAddress();
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        Customer customer = purchase.getCustomer();
        customer.addOrder(order);
        System.out.println(order);
        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
