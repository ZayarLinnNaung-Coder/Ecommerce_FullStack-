package com.zware.ecommercebackend.dto;

import com.zware.ecommercebackend.entity.Address;
import com.zware.ecommercebackend.entity.Customer;
import com.zware.ecommercebackend.entity.Order;
import com.zware.ecommercebackend.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
