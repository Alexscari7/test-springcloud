package com.github.alexscari.service.impl;

import com.github.alexscari.entity.OrderEntity;
import com.github.alexscari.feignclient.AccountClient;
import com.github.alexscari.feignclient.StorageClient;
import com.github.alexscari.mapper.OrderMapper;
import com.github.alexscari.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wusd
 * @since 2022-02-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final StorageClient storageClient;

    private final AccountClient accountClient;

    private static final int STATUS_UNF = 0;

    private static final int STATUS_FIN = 1;


    @Autowired
    public OrderServiceImpl(StorageClient storageClient, AccountClient accountClient) {
        this.storageClient = storageClient;
        this.accountClient = accountClient;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(rollbackFor = Exception.class)
    public OrderEntity create(long userId, long productId, int count, BigDecimal money) {
        // 开始生成订单
        OrderEntity orderEntity = generateOrder(userId, productId, count, money);
        // 扣减库存
        storageClient.deduct(productId, count);
        // 账户金额减少
        accountClient.decrease(userId, money);
        // 完成订单
        finishOrder(orderEntity);
        return orderEntity;
    }

    public OrderEntity generateOrder(long userId, long productId, int count, BigDecimal money) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setProductId(productId);
        orderEntity.setCount(count);
        orderEntity.setMoney(money);
        orderEntity.setStatus(STATUS_UNF);
        boolean save = save(orderEntity);
        if (save) {
            LOGGER.info("订单开始生成。。。");
        }
        return orderEntity;
    }

    public void finishOrder(OrderEntity orderEntity) {
        orderEntity.setStatus(STATUS_FIN);
        boolean update = updateById(orderEntity);
        if (update) {
            LOGGER.info("订单已完成。。。");
        }
    }
}
