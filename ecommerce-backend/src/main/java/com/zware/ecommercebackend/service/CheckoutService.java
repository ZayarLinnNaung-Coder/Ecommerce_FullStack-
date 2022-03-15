package com.zware.ecommercebackend.service;

import com.zware.ecommercebackend.dto.Purchase;
import com.zware.ecommercebackend.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
