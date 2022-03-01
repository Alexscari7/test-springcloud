package com.github.alexscari.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.alexscari.entity.StorageEntity;
import com.github.alexscari.mapper.StorageMapper;
import com.github.alexscari.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wusd
 * @since 2022-03-01
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StorageEntity> implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Override
    public StorageEntity selectByProductId(long productId) {
        QueryWrapper<StorageEntity> queryWrapper = Wrappers.query();
        queryWrapper.eq("product_id", productId);
        return getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deduct(long productId, int count) {
        StorageEntity storage = selectByProductId(productId);
        if (storage == null) {
            LOGGER.error("商品[{}]不存在", productId);
            throw new RuntimeException("商品不存在");
        } else if (storage.getResidue() < count) {
            LOGGER.error("库存不足，商品[{}]，剩余库存[{}]，预期扣减[{}]", productId, storage.getResidue(), count);
            throw new RuntimeException("库存不足");
        } else {
            UpdateWrapper<StorageEntity> updateWrapper = Wrappers.update();
            updateWrapper.set("used", storage.getUsed() + count);
            updateWrapper.set("residue", storage.getResidue() - count);
            updateWrapper.eq("product_id", productId);
            boolean update = update(updateWrapper);
            if (update) {
                LOGGER.info("扣减库存成功，商品[{}]，剩余库存[{}]，已扣减[{}]", productId, storage.getResidue() - count, count);
            }
        }
    }
}
