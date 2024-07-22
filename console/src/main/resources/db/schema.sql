-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_suite
-- ----------------------------
CREATE TABLE `t_suite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `suite_code` varchar(20) DEFAULT NULL,
  `suite_name` varchar(30) DEFAULT NULL,
  `suite_classify_id` bigint DEFAULT NULL,
  `suite_image` text,
  `suite_version` varchar(10) DEFAULT NULL,
  `suite_desc` varchar(140) DEFAULT NULL,
  `suite_help_doc_json` varchar(300) DEFAULT NULL,
  `suite_flag` int DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_api
-- ----------------------------
CREATE TABLE `t_api` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `suite_id` bigint(20) DEFAULT NULL,
 `api_name` varchar(50) DEFAULT NULL,
 `api_url` varchar(100) DEFAULT NULL,
 `api_desc` varchar(200) DEFAULT NULL,
 `api_request_type` varchar(10) DEFAULT NULL,
 `api_request_content_type` varchar(40) DEFAULT NULL,
 `deleted` int DEFAULT NULL,
 `created_at` datetime DEFAULT NULL,
 `created_by` int(11) DEFAULT NULL,
 `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
 `updated_by` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_object
-- ----------------------------
CREATE TABLE `t_object` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `object_key` varchar(30) DEFAULT NULL,
 `object_name` varchar(50) DEFAULT NULL,
 `object_desc` varchar(200) DEFAULT NULL,
 `deleted` int DEFAULT NULL,
 `created_at` datetime DEFAULT NULL,
 `created_by` bigint DEFAULT NULL,
 `updated_at` datetime DEFAULT NULL,
 `updated_by` bigint DEFAULT NULL,
 PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_flow_definition
-- ----------------------------
CREATE TABLE `t_flow_definition` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `flow_key` varchar(20) DEFAULT NULL,
 `flow_name` varchar(60) DEFAULT NULL,
 `flow_type` varchar(8) DEFAULT NULL,
 `flow_content` mediumtext,
 `remark` varchar(200) DEFAULT NULL,
 `deleted` int DEFAULT NULL,
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
  `param_position` varchar(20) DEFAULT NULL,
  `param_desc` varchar(100) DEFAULT NULL,
  `data_type` text,
  `required` int(2) DEFAULT NULL,
  `source_type` varchar(8) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `deleted` int DEFAULT NULL,
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
    `data_type` text,
    `created_at` datetime DEFAULT NULL,
    `created_by` int(11) DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_flow_info
-- ----------------------------
CREATE TABLE `t_flow_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `flow_key` varchar(20) DEFAULT NULL,
  `flow_name` varchar(60) DEFAULT NULL,
  `flow_type` varchar(8) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleted` int DEFAULT NULL,
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
  `flow_version_remark` varchar(200) DEFAULT NULL,
  `flow_content` mediumtext,
  `inputs` text,
  `outputs` text,
  `variables` text,
  `deleted` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_token
-- ----------------------------
CREATE TABLE `t_token` (
   `id` int NOT NULL AUTO_INCREMENT,
   `token_value` varchar(150) DEFAULT NULL,
   `token_desc` varchar(200) DEFAULT NULL,
   `deleted` int DEFAULT NULL,
   `created_at` datetime DEFAULT NULL,
   `created_by` int DEFAULT NULL,
   `updated_at` datetime DEFAULT NULL,
   `updated_by` int DEFAULT NULL,
   PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_data_source
-- ----------------------------
CREATE TABLE `t_data_source` (
   `id` int NOT NULL AUTO_INCREMENT,
   `data_source_name` varchar(150) DEFAULT NULL,
   `data_source_type` varchar(20) DEFAULT NULL,
   `data_source_desc` varchar(200) DEFAULT NULL,
   `address` varchar(100) DEFAULT NULL,
   `port` varchar(10) DEFAULT NULL,
   `user_name` varchar(40) DEFAULT NULL,
   `password` varchar(40) DEFAULT NULL,
   `database_name` varchar(20) DEFAULT NULL,
   `connect_ext_info` varchar(200) DEFAULT NULL,
   `min_pool_size` int(4) DEFAULT NULL,
   `max_pool_size` int(4) DEFAULT NULL,
   `query_timeout` int(4) DEFAULT NULL,
   `data_source_ext_info` varchar(200) DEFAULT NULL,
   `deleted` int DEFAULT NULL,
   `created_at` datetime DEFAULT NULL,
   `created_by` int DEFAULT NULL,
   `updated_at` datetime DEFAULT NULL,
   `updated_by` int DEFAULT NULL,
   PRIMARY KEY (`id`)
);