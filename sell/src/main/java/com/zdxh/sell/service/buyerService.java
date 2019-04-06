package com.zdxh.sell.service;

import com.zdxh.sell.dto.OrderDTO;

public interface buyerService {
    /**
     * 查询一个订单
     */
    OrderDTO findOrderOne(String openid,String orderId);
    /**
     * 取消订单
     */
    OrderDTO cancelOrder(String openid,String orderId);
}
