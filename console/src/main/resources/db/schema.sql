-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_domain
-- ----------------------------
CREATE TABLE `t_domain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `domain_code` varchar(30) DEFAULT NULL,
  `domain_name` varchar(30) DEFAULT NULL,
  `domain_desc` varchar(30) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_api
-- ----------------------------
CREATE TABLE `t_api` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `domain_id` bigint(20) DEFAULT NULL,
 `api_name` varchar(50) DEFAULT NULL,
 `api_url` varchar(100) DEFAULT NULL,
 `api_desc` varchar(200) DEFAULT NULL,
 `api_request_type` varchar(10) DEFAULT NULL,
 `api_request_content_type` varchar(40) DEFAULT NULL,
 `created_at` datetime DEFAULT NULL,
 `created_by` int(11) DEFAULT NULL,
 `updated_at` datetime DEFAULT NULL,
 `updated_by` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`)
);