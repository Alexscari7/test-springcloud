package com.github.alexscari.service;

import com.github.alexscari.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wusd
 * @since 2022-02-28
 */
public interface OrderService extends IService<OrderEntity> {

    OrderEntity create(long userId, long productId, int count, BigDecimal money);

}
