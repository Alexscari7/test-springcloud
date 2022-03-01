package com.github.alexscari.service.impl;

import com.github.alexscari.entity.OrderEntity;
import com.github.alexscari.mapper.OrderMapper;
import com.github.alexscari.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wusd
 * @since 2022-02-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

}
