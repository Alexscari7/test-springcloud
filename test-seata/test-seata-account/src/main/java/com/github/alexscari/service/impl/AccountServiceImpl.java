package com.github.alexscari.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.alexscari.entity.AccountEntity;
import com.github.alexscari.mapper.AccountMapper;
import com.github.alexscari.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wusd
 * @since 2022-03-01
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public AccountEntity selectByUserId(long userId) {
        QueryWrapper<AccountEntity> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", userId);
        return getOne(queryWrapper);
    }

    @Override
    @Transactional
    public void decrease(long userId, BigDecimal money) {
        AccountEntity account = selectByUserId(userId);
        if (account == null) {
            LOGGER.error("账户[{}]不存在", userId);
            throw new RuntimeException("账户不存在");
        } else if (account.getResidue().compareTo(money) < 0) {
            LOGGER.error("余额不足，账户[{}]，余额[{}]，预期扣减[{}]", userId, account.getResidue(), money);
            throw new RuntimeException("余额不足");
        } else {
            UpdateWrapper<AccountEntity> updateWrapper = Wrappers.update();
            updateWrapper.set("used", account.getUsed().add(money));
            updateWrapper.set("residue", account.getResidue().subtract(money));
            updateWrapper.eq("user_id", userId);
            boolean update = update(updateWrapper);
            if (update) {
                LOGGER.info("账户扣减成功，账户[{}]，余额[{}]，已扣减[{}]",
                        userId, account.getResidue().subtract(money).toPlainString(), money.toPlainString());
            }
        }
    }
}
