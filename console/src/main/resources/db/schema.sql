
-- ----------------------------
-- Table structure for t_domain
-- ----------------------------
DROP TABLE IF EXISTS `t_domain`;
CREATE TABLE `t_domain` (
     `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
     `domain_code` VARCHAR(30) DEFAULT NULL,
     `domain_name` VARCHAR(30) DEFAULT NULL,
     `domain_desc` VARCHAR(100) DEFAULT NULL,
     `created_at` DATETIME DEFAULT NULL,
     `created_by` BIGINT(20) DEFAULT NULL,
     `updated_at` DATETIME DEFAULT NULL,
     `updated_by` BIGINT(20) DEFAULT NULL,
     PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_api
-- ----------------------------
DROP TABLE IF EXISTS `t_api`;
CREATE TABLE `t_api` (
      `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
      `domain_id` BIGINT(20) DEFAULT NULL COMMENT '领域Id',
      `api_name` VARCHAR(50) DEFAULT NULL COMMENT 'api名称',
      `api_url` VARCHAR(100) DEFAULT NULL COMMENT 'api接口地址',
      `api_desc` VARCHAR(200) DEFAULT NULL COMMENT 'api描述',
      `api_request_type` VARCHAR(10) DEFAULT NULL COMMENT 'api请求类型  GET POST PUT',
      `api_request_content_type` VARCHAR(40) DEFAULT NULL COMMENT 'api请求内容类型 application/json',
      `created_at` DATETIME DEFAULT NULL COMMENT '创建时间',
      `created_by` BIGINT(11) DEFAULT NULL COMMENT '创建人',
      `updated_at` DATETIME DEFAULT NULL COMMENT '修改时间',
      `updated_by` BIGINT(11) DEFAULT NULL COMMENT '修改人',
      PRIMARY KEY (`id`)
);
