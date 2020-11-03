package com.wxc.service.impl;

import com.wxc.dao.OrderMapper;
import com.wxc.dao.SequenceMapper;
import com.wxc.entity.Order;
import com.wxc.entity.Sequence;
import com.wxc.error.BuinessException;
import com.wxc.error.EmBuinessError;
import com.wxc.service.ItemService;
import com.wxc.service.OrderService;
import com.wxc.service.UserService;
import com.wxc.service.model.ItemModel;
import com.wxc.service.model.OrderModel;
import com.wxc.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.BatchUpdateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SequenceMapper sequenceMapper;
    @Override
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount, Integer promoId) throws BuinessException {
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR,"商品信息不正确");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR, "用户信息不正确");

        }
        if(amount<0||amount>=99){
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR, "数量信息不正确");

        }
        if (promoId != null) {
            if(promoId.intValue()!=itemModel.getPromoModel().getId()){
                throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR, "秒杀活动信息不正确");

            } else if (itemModel.getPromoModel().getStatus() != 2) {
                throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR, "秒杀互动未开始");

            }
        }
        boolean result = itemService.decreaseStock(itemId,amount);
        if (!result) {
            throw new BuinessException(EmBuinessError.STOCK_NOT_ENOUGH);
        }
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));
        orderModel.setId(generateOrderNo());
        Order order = this.convertFromOrderModel(orderModel);
        orderMapper.insertSelective(order);
        itemService.increaseSales(itemId,amount);
        return orderModel;
    }

    private Order convertFromOrderModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        Order order = new Order();
        BeanUtils.copyProperties(orderModel, order);
        return order;
    }

    private String generateOrderNo() {
        StringBuilder stringBuilder = new StringBuilder();
        //订单号有16位 前8位为时间信息，年月日，中间6位为自增序列，最后2位为分库分表位暂时写死
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);
        //获取当前sequence
        int seq = 0;
        Sequence sequence = sequenceMapper.getSequenceByName("order_info");
        seq = sequence.getCurrentValue();
        sequence.setCurrentValue(sequence.getCurrentValue() + sequence.getStep());
        sequenceMapper.updateByPrimaryKey(sequence);
        String sequenceStr = String.valueOf(seq);
        for (int i = 0; i < 6 - sequenceStr.length(); i ++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);
        stringBuilder.append("00");
        return stringBuilder.toString();
    }
}
