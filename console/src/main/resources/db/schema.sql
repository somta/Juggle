-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
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
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
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
 `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
 `updated_by` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_data_type_info
-- ----------------------------
CREATE TABLE `t_data_type_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_type_classify` int(2) DEFAULT NULL,
  `data_type` varchar(20) DEFAULT NULL,
  `display_name` varchar(20) DEFAULT NULL,
  `object_key` varchar(30) DEFAULT NULL,
  `object_structure` varchar(1000) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_obj
-- ----------------------------
CREATE TABLE `t_object` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `object_code` varchar(30) DEFAULT NULL,
 `object_name` varchar(50) DEFAULT NULL,
 `object_desc` varchar(100) DEFAULT NULL,
 `created_at` datetime DEFAULT NULL,
 `created_by` bigint DEFAULT NULL,
 `updated_at` datetime DEFAULT NULL,
 `updated_by` bigint DEFAULT NULL,
 PRIMARY KEY (`id`)
)

-- ----------------------------
-- Table structure for t_flow_definition
-- ----------------------------
CREATE TABLE `t_flow_definition` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `flow_key` varchar(20) DEFAULT NULL,
 `flow_name` varchar(60) DEFAULT NULL,
 `flow_type` varchar(8) DEFAULT NULL,
 `flow_content` mediumtext,
 `remark` varchar(120) DEFAULT NULL,
 `created_at` datetime DEFAULT NULL,
 `created_by` bigint(20) DEFAULT NULL,
 `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
 `updated_by` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_parameter
-- ----------------------------
CREATE TABLE `t_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_type` int(2) DEFAULT NULL,
  `param_key` varchar(20) DEFAULT NULL,
  `param_name` varchar(30) DEFAULT NULL,
  `data_type` varchar(200) DEFAULT NULL,
  `required` int(2) DEFAULT NULL,
  `source_type` varchar(8) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_variable_info
-- ----------------------------
CREATE TABLE `t_variable_info` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flow_definition_id` bigint(20) DEFAULT NULL,
    `env_key` varchar(30) DEFAULT NULL,
    `env_name` varchar(30) DEFAULT NULL,
    `env_type` int(1) DEFAULT NULL,
    `data_type` varchar(255) DEFAULT NULL,
    `created_at` datetime DEFAULT NULL,
    `created_by` int(11) DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_flow
-- ----------------------------
CREATE TABLE `t_flow_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `flow_key` varchar(20) DEFAULT NULL,
  `flow_name` varchar(60) DEFAULT NULL,
  `flow_type` varchar(8) DEFAULT NULL,
  `remark` varchar(120) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_flow_version
-- ----------------------------
CREATE TABLE `t_flow_version` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `flow_id` bigint DEFAULT NULL,
  `flow_version` varchar(8) DEFAULT NULL,
  `flow_version_status` int(1) DEFAULT 0,
  `flow_version_remark` varchar(250) DEFAULT NULL,
  `flow_content` mediumtext,
  `inputs` text,
  `outputs` text,
  `variables` text,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);