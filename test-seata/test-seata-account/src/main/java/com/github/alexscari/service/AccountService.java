package com.github.alexscari.service;

import com.github.alexscari.entity.AccountEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wusd
 * @since 2022-03-01
 */
public interface AccountService extends IService<AccountEntity> {

    AccountEntity selectByUserId(long userId);

    void decrease(long userId, BigDecimal money);

}
