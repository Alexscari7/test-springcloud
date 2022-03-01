DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `user_id` bigint DEFAULT NULL COMMENT '用户id',
                           `product_id` bigint DEFAULT NULL COMMENT '产品id',
                           `count` int DEFAULT NULL COMMENT '数量',
                           `money` decimal(11,0) DEFAULT NULL COMMENT '金额',
                           `status` int DEFAULT NULL COMMENT '订单状态：0：未完成；1：已完结',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `product_id` bigint DEFAULT NULL COMMENT '产品id',
                             `total` int DEFAULT NULL COMMENT '总库存',
                             `used` int DEFAULT NULL COMMENT '已用库存',
                             `residue` int DEFAULT NULL COMMENT '剩余库存',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `t_storage` VALUES ('1', '1', '100', '0', '100');

DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `user_id` bigint DEFAULT NULL COMMENT '用户id',
                             `total` decimal(10,0) DEFAULT NULL COMMENT '总额度',
                             `used` decimal(10,0) DEFAULT NULL COMMENT '已用余额',
                             `residue` decimal(10,0) DEFAULT '0' COMMENT '剩余可用额度',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `t_account` VALUES ('1', '1', '1000', '0', '1000');