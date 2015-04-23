-- ----------------------------
-- Table structure for `wechat_user_fund`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_fund`;
CREATE TABLE `wechat_user_fund` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `fund_account` varchar(255) DEFAULT NULL,
  `amount_month` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FUND_USER` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

