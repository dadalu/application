package com.wxc.service;

import com.wxc.error.BuinessException;
import com.wxc.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId,Integer itemId,Integer amount,Integer promoId) throws BuinessException;
}
