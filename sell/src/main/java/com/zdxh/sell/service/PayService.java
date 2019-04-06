package com.zdxh.sell.service;

import com.zdxh.sell.dto.OrderDTO;

public interface PayService {
    void create(OrderDTO orderDTO);
}
