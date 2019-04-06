package com.zdxh.sell.service.impl;

import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.zdxh.sell.dto.OrderDTO;
import com.zdxh.sell.service.PayService;

public class PayServiceImpl implements PayService {
    @Override
    public void create(OrderDTO orderDTO) {
        BestPayServiceImpl bestPayService=new BestPayServiceImpl();
    }
}
