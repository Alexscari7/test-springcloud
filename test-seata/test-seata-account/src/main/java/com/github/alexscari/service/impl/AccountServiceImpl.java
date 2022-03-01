package com.github.alexscari.service.impl;

import com.github.alexscari.entity.AccountEntity;
import com.github.alexscari.mapper.AccountMapper;
import com.github.alexscari.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
