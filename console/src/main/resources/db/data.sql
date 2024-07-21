INSERT INTO `t_user` (`id`, `user_name`, `password`, `deleted`) VALUES (1, 'juggle', '24cb6bcbc65730e9650745d379613563', 0);


INSERT INTO `t_suite` (`id`, `suite_code`, `suite_name`, `suite_classify_id`, `suite_image`, `suite_version`, `suite_desc`, `suite_help_doc_json`, `suite_flag`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'system_api', '系统示例接口套件', NULL, NULL, 'v1.0.0', '这是系统内置的一个测试接口', NULL, 1, 0, '2024-04-06 23:17:36', 1, '2024-04-07 00:39:37', NULL);


INSERT INTO `t_api` (`id`, `suite_id`, `api_name`, `api_url`, `api_desc`, `api_request_type`, `api_request_content_type`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 1, '登录接口', 'http://127.0.0.1:9127/example/user/login', '', 'POST', 'application/json', 0, '2024-03-08 22:16:37', 1, '2024-07-02 23:29:33', 1);
INSERT INTO `t_api` (`id`, `suite_id`, `api_name`, `api_url`, `api_desc`, `api_request_type`, `api_request_content_type`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 1, '获取用户信息', 'http://127.0.0.1:9127/example/user/getUserById', '获取用户信息-测试接口', 'GET', 'application/x-www-form-urlencoded', 0, '2024-03-09 22:32:22', 1, '2024-07-02 23:29:33', 1);
INSERT INTO `t_api` (`id`, `suite_id`, `api_name`, `api_url`, `api_desc`, `api_request_type`, `api_request_content_type`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (8, 1, '发布商品', 'http://127.0.0.1:9127/example/goods/releaseGoods', '', 'GET', 'application/json', 0, '2024-03-10 22:28:57', 1, '2024-07-02 23:29:33', 1);
INSERT INTO `t_api` (`id`, `suite_id`, `api_name`, `api_url`, `api_desc`, `api_request_type`, `api_request_content_type`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (9, 1, '获取商品详情', 'http://127.0.0.1:9127/example/goods/getGoodsInfo', '', 'GET', 'application/json', 0, '2024-03-10 22:33:11', 1, '2024-07-02 23:29:33', 1);
INSERT INTO `t_api` (`id`, `suite_id`, `api_name`, `api_url`, `api_desc`, `api_request_type`, `api_request_content_type`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (15, 1, '购买商品', 'http://127.0.0.1:9127/example/order/placeOrder', '', 'POST', 'application/x-www-form-urlencoded', 0, '2024-03-10 22:43:09', 1, '2024-07-02 23:29:33', 1);
INSERT INTO `t_api` (`id`, `suite_id`, `api_name`, `api_url`, `api_desc`, `api_request_type`, `api_request_content_type`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (16, 1, '获取订单列表', 'http://127.0.0.1:9127/example/order/getUserOrderList', '', 'GET', 'application/x-www-form-urlencoded', 0, '2024-03-10 23:06:32', 1, '2024-07-02 23:29:33', 1);
INSERT INTO `t_api` (`id`, `suite_id`, `api_name`, `api_url`, `api_desc`, `api_request_type`, `api_request_content_type`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (17, 1, '查询订单详情', 'http://127.0.0.1:9127/example/order/getOrderByNo', '查询订单信息 -测试接口', 'GET', 'application/x-www-form-urlencoded', 0, '2024-03-11 22:24:25', 1, '2024-07-02 23:29:33', 1);


INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'userName', '用户名', NULL, '{"type":"String"}', 1, 'api', 1, '2024-07-02 15:34:12', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'password', '密码', NULL, '{"type":"String"}', 1, 'api', 1, '2024-07-02 15:34:12', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'userName', '用户名', NULL, '{"type":"String"}', NULL, 'api', 1, '2024-07-02 15:34:12', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'loginFlag', '是否登录成功', NULL, '{"type":"Boolean"}', NULL, 'api', 1, '2024-07-02 15:34:12', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'userId', '用户ID', NULL, '{"type":"Integer"}', NULL, 'api', 1, '2024-07-02 15:34:12', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'userId', '用户ID', NULL, '{"type":"Integer"}', 1, 'api', 2, '2024-07-02 15:37:41', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'id', '用户ID', NULL, '{"type":"Integer"}', NULL, 'api', 2, '2024-07-02 15:37:41', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'name', '用户姓名', NULL, '{"type":"String"}', NULL, 'api', 2, '2024-07-02 15:37:41', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'age', '用户年龄', NULL, '{"type":"Integer"}', NULL, 'api', 2, '2024-07-02 15:37:41', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'birthday', '用户生日', NULL, '{"type":"Date"}', NULL, 'api', 2, '2024-07-02 15:37:41', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'goodsName', '商品名称', NULL, '{"type":"String"}', 0, 'api', 8, '2024-07-02 15:43:57', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'goodsInventory', '商品库存', NULL, '{"type":"Integer"}', 0, 'api', 8, '2024-07-02 15:43:57', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'goodsId', '商品ID', NULL, '{"type":"Integer"}', NULL, 'api', 8, '2024-07-02 15:43:57', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'goodsName', '商品名称', NULL, '{"type":"String"}', NULL, 'api', 8, '2024-07-02 15:43:57', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'goodsInventory', '商品库存', NULL, '{"type":"Integer"}', NULL, 'api', 8, '2024-07-02 15:43:57', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'goodsName', '商品名称', NULL, '{"type":"String"}', 1, 'api', 9, '2024-07-02 15:47:55', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'goodsInventory', '商品库存', NULL, '{"type":"String"}', 0, 'api', 9, '2024-07-02 15:47:55', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'goodsId', '商品ID', NULL, '{"type":"Integer"}', NULL, 'api', 9, '2024-07-02 15:47:55', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'goodsName', '商品名称', NULL, '{"type":"String"}', NULL, 'api', 9, '2024-07-02 15:47:55', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'goodsPrice', '商品价格', NULL, '{"type":"Double"}', NULL, 'api', 9, '2024-07-02 15:47:55', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'goodsInventory', '商品库存', NULL, '{"type":"Integer"}', NULL, 'api', 9, '2024-07-02 15:47:55', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'orderName', '订单名称', NULL, '{"type":"String"}', 0, 'api', 15, '2024-07-02 15:50:33', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'userId', '用户ID', NULL, '{"type":"Integer"}', 0, 'api', 15, '2024-07-02 15:50:33', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'orderNo', '订单号', NULL, '{"type":"String"}', NULL, 'api', 15, '2024-07-02 15:50:33', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'orderName', '订单名称', NULL, '{"type":"String"}', NULL, 'api', 15, '2024-07-02 15:50:33', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'userId', '用户ID', NULL, '{"type":"Integer"}', NULL, 'api', 15, '2024-07-02 15:50:33', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'userId', '用户ID', NULL, '{"type":"Integer"}', 0, 'api', 16, '2024-07-02 16:35:20', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'orderName', '订单名称', NULL, '{"type":"String"}', 0, 'api', 16, '2024-07-02 16:35:20', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'userId', '用户ID', NULL, '{"type":"Integer"}', NULL, 'api', 16, '2024-07-02 16:35:20', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'orderList', '订单列表', NULL, '{"type":"List","itemType":"Object","objectKey":"OrderDTO","objectStructure":[{"propKey":"userId","propName":"用户ID","dataType":{"type":"Integer"}},{"propKey":"orderName","propName":"订单名称","dataType":{"type":"String"}},{"propKey":"orderNo","propName":"订单号","dataType":{"type":"String"}}]}', NULL, 'api', 16, '2024-07-02 16:35:20', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'orderNo', '订单号', NULL, '{"type":"String"}', 1, 'api', 17, '2024-07-02 16:39:09', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'orderNo', '订单号', NULL, '{"type":"String"}', NULL, 'api', 17, '2024-07-02 16:39:09', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'orderName', '订单名称', NULL, '{"type":"String"}', NULL, 'api', 17, '2024-07-02 16:39:09', NULL, NULL, NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'userId', '用户ID', NULL, '{"type":"Integer"}', NULL, 'api', 17, '2024-07-02 16:39:09', NULL, NULL, NULL);


INSERT INTO `t_object` (`id`, `object_key`, `object_name`, `object_desc`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'OrderDTO', '订单传输对象', '用于示例接口的对象', 0, '2024-07-02 16:29:35', 1, '2024-07-02 16:29:57', 1);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (3, 'orderNo', '订单号', NULL, '{"type":"String"}', NULL, 'object', 1, '2024-07-02 16:29:57', NULL, '2024-07-03 23:58:28', NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (3, 'orderName', '订单名称', NULL, '{"type":"String"}', NULL, 'object', 1, '2024-07-02 16:29:57', NULL, '2024-07-03 23:58:28', NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (3, 'userId', '用户ID', NULL, '{"type":"Integer"}', NULL, 'object', 1, '2024-07-02 16:29:57', NULL, '2024-07-03 23:58:28', NULL);


INSERT INTO `t_flow_definition` (`id`, `flow_key`, `flow_name`, `flow_type`, `flow_content`, `remark`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'sync_example', '示例流程', 'sync', '[{"key":"start_KaiQsdEC","name":"示例流程","elementType":"START","outgoings":["method_h0h73z8o"]},{"key":"end_KqdO1w6v","name":"结束","elementType":"END","incomings":["method_wt93gori"]},{"key":"method_h0h73z8o","name":"用户登录","elementType":"METHOD","desc":"","incomings":["start_KaiQsdEC"],"outgoings":["condition_w98rq3r8"],"method":{"suiteId":1,"methodId":1,"url":"http://127.0.0.1:9127/example/user/login","requestType":"POST","requestContentType":"application/json","headerFillRules":[],"inputFillRules":[{"source":"input_userName","sourceType":"VARIABLE","sourceDataType":null,"target":"userName","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}},{"source":"input_password","sourceType":"VARIABLE","sourceDataType":null,"target":"password","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}}],"outputFillRules":[{"source":"userName","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"String"},"target":"output_userName","targetType":"VARIABLE","targetDataType":{"type":"String"}},{"source":"loginFlag","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"Boolean"},"target":"env_isLogin","targetType":"VARIABLE","targetDataType":{"type":"Boolean"}},{"source":"userId","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"Integer"},"target":"env_userId","targetType":"VARIABLE","targetDataType":{"type":"Integer"}}]}},{"key":"condition_w98rq3r8","name":"判断节点","elementType":"CONDITION","desc":"","incomings":["method_h0h73z8o"],"outgoings":["end_KqdO1w6v"],"conditions":[{"conditionName":"登录成功","conditionType":"CUSTOM","outgoing":"method_98a23hlu","expression":"env_isLogin==true","conditionExpressions":[[{"envKey":"env_isLogin","dataType":{"type":"Boolean"},"operator":"equal","assignType":"CONSTANT","value":true}]]},{"conditionName":"默认else分支","conditionType":"DEFAULT","outgoing":"end_KqdO1w6v","expression":"","conditionExpressions":[]}]},{"key":"method_98a23hlu","name":"查询用户信息","elementType":"METHOD","desc":"","incomings":["condition_w98rq3r8"],"outgoings":["condition_pq6w963k"],"method":{"suiteId":1,"methodId":2,"url":"http://127.0.0.1:9127/example/user/getUserById","requestType":"GET","requestContentType":"application/x-www-form-urlencoded","headerFillRules":[],"inputFillRules":[{"source":"env_userId","sourceType":"VARIABLE","sourceDataType":null,"target":"userId","targetType":"INPUT_PARAM","targetDataType":{"type":"Integer"}}],"outputFillRules":[{"source":"age","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"Integer"},"target":"output_age","targetType":"VARIABLE","targetDataType":{"type":"Integer"}}]}},{"key":"condition_pq6w963k","name":"判断节点","elementType":"CONDITION","desc":"","incomings":["method_98a23hlu"],"outgoings":["end_KqdO1w6v"],"conditions":[{"conditionName":"存款小于10万","conditionType":"CUSTOM","outgoing":"method_2w5t1jbt","expression":"input_deposit<=100000","conditionExpressions":[[{"envKey":"input_deposit","dataType":{"type":"Double","itemType":""},"operator":"lessThanOrEqual","assignType":"CONSTANT","value":100000}]]},{"conditionName":"存款大于10万","conditionType":"CUSTOM","outgoing":"method_wt93gori","expression":"input_deposit>100000","conditionExpressions":[[{"envKey":"input_deposit","dataType":{"type":"Double","itemType":""},"operator":"greaterThan","assignType":"CONSTANT","value":100000}]]},{"conditionName":"默认else分支","conditionType":"DEFAULT","outgoing":"end_KqdO1w6v","expression":"","conditionExpressions":[]}]},{"key":"method_2w5t1jbt","name":"送10元话费","elementType":"METHOD","desc":"","incomings":["condition_pq6w963k"],"outgoings":["end_KqdO1w6v"],"method":{"suiteId":1,"methodId":15,"url":"http://127.0.0.1:9127/example/order/placeOrder","requestType":"POST","requestContentType":"application/x-www-form-urlencoded","headerFillRules":[],"inputFillRules":[{"source":"env_userId","sourceType":"VARIABLE","sourceDataType":null,"target":"userId","targetType":"INPUT_PARAM","targetDataType":{"type":"Integer"}},{"source":"送10元话费","sourceType":"CONSTANT","sourceDataType":null,"target":"orderName","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}}],"outputFillRules":[{"source":"orderName","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"String"},"target":"output_orderName","targetType":"VARIABLE","targetDataType":{"type":"String"}}]}},{"key":"method_wt93gori","name":"送一双耐克的鞋","elementType":"METHOD","desc":"","incomings":["condition_pq6w963k"],"outgoings":["end_KqdO1w6v"],"method":{"suiteId":1,"methodId":15,"url":"http://127.0.0.1:9127/example/order/placeOrder","requestType":"POST","requestContentType":"application/x-www-form-urlencoded","headerFillRules":[],"inputFillRules":[{"source":"env_userId","sourceType":"VARIABLE","sourceDataType":null,"target":"userId","targetType":"INPUT_PARAM","targetDataType":{"type":"Integer"}},{"source":"送一双耐克的鞋","sourceType":"CONSTANT","sourceDataType":null,"target":"orderName","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}}],"outputFillRules":[{"source":"orderName","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"String"},"target":"output_orderName","targetType":"VARIABLE","targetDataType":{"type":"String"}}]}}]', '', 0, '2024-07-02 10:22:33', 1, '2024-07-04 00:01:00', 1);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'userName', '用户名称', NULL, '{"type":"String"}', 0, 'flow', 1, '2024-07-03 15:28:34', NULL, '2024-07-04 00:01:08', NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'password', '密码', NULL, '{"type":"String"}', 0, 'flow', 1, '2024-07-03 15:28:34', NULL, '2024-07-04 00:01:08', NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'deposit', '存款', NULL, '{"type":"Double"}', 0, 'flow', 1, '2024-07-03 15:28:34', NULL, '2024-07-04 00:01:08', NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'userName', '用户名称', NULL, '{"type":"String"}', NULL, 'flow', 1, '2024-07-03 15:28:34', NULL, '2024-07-04 00:01:08', NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'age', '用户年龄', NULL, '{"type":"Integer"}', NULL, 'flow', 1, '2024-07-03 15:28:34', NULL, '2024-07-04 00:01:08', NULL);
INSERT INTO `t_parameter` (`param_type`, `param_key`, `param_name`, `param_desc`, `data_type`, `required`, `source_type`, `source_id`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'orderName', '订单名称', NULL, '{"type":"String"}', NULL, 'flow', 1, '2024-07-03 15:28:34', NULL, '2024-07-04 00:01:08', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'env_isLogin', '是否登录成功变量', 3, '{"type":"Boolean"}', NULL, NULL, '2024-07-04 00:00:53', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'env_userId', '用户ID变量', 3, '{"type":"Integer"}', NULL, NULL, '2024-07-04 00:00:53', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'input_userName', '用户名称', 1, '{"type":"String"}', NULL, NULL, '2024-07-04 00:00:54', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'input_password', '密码', 1, '{"type":"String"}', NULL, NULL, '2024-07-04 00:00:54', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'input_deposit', '存款', 1, '{"type":"Double","itemType":""}', NULL, NULL, '2024-07-04 00:00:54', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'output_userName', '用户名称', 2, '{"type":"String"}', NULL, NULL, '2024-07-04 00:00:54', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'output_age', '用户年龄', 2, '{"type":"Integer"}', NULL, NULL, '2024-07-04 00:00:54', NULL);
INSERT INTO `t_variable_info` (`flow_definition_id`, `env_key`, `env_name`, `env_type`, `data_type`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'output_orderName', '订单名称', 2, '{"type":"String"}', NULL, NULL, '2024-07-04 00:00:54', NULL);

INSERT INTO `t_flow_info` (`id`, `flow_key`, `flow_name`, `flow_type`, `remark`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'sync_example', '示例流程', 'sync', '', 0, '2024-07-04 00:09:26', 1, '2024-07-04 00:10:30', NULL);
INSERT INTO `t_flow_version` (`id`, `flow_id`, `flow_version`, `flow_version_status`, `flow_version_remark`, `flow_content`, `inputs`, `outputs`, `variables`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 1, 'v1', 1, '', '[{"key":"start_KaiQsdEC","name":"示例流程","elementType":"START","outgoings":["method_h0h73z8o"]},{"key":"end_KqdO1w6v","name":"结束","elementType":"END","incomings":["method_wt93gori"]},{"key":"method_h0h73z8o","name":"用户登录","elementType":"METHOD","desc":"","incomings":["start_KaiQsdEC"],"outgoings":["condition_w98rq3r8"],"method":{"suiteId":1,"methodId":1,"url":"http://127.0.0.1:9127/example/user/login","requestType":"POST","requestContentType":"application/json","headerFillRules":[],"inputFillRules":[{"source":"input_userName","sourceType":"VARIABLE","sourceDataType":null,"target":"userName","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}},{"source":"input_password","sourceType":"VARIABLE","sourceDataType":null,"target":"password","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}}],"outputFillRules":[{"source":"userName","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"String"},"target":"output_userName","targetType":"VARIABLE","targetDataType":{"type":"String"}},{"source":"loginFlag","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"Boolean"},"target":"env_isLogin","targetType":"VARIABLE","targetDataType":{"type":"Boolean"}},{"source":"userId","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"Integer"},"target":"env_userId","targetType":"VARIABLE","targetDataType":{"type":"Integer"}}]}},{"key":"condition_w98rq3r8","name":"判断节点","elementType":"CONDITION","desc":"","incomings":["method_h0h73z8o"],"outgoings":["end_KqdO1w6v"],"conditions":[{"conditionName":"登录成功","conditionType":"CUSTOM","outgoing":"method_98a23hlu","expression":"env_isLogin==true","conditionExpressions":[[{"envKey":"env_isLogin","dataType":{"type":"Boolean"},"operator":"equal","assignType":"CONSTANT","value":true}]]},{"conditionName":"默认else分支","conditionType":"DEFAULT","outgoing":"end_KqdO1w6v","expression":"","conditionExpressions":[]}]},{"key":"method_98a23hlu","name":"查询用户信息","elementType":"METHOD","desc":"","incomings":["condition_w98rq3r8"],"outgoings":["condition_pq6w963k"],"method":{"suiteId":1,"methodId":2,"url":"http://127.0.0.1:9127/example/user/getUserById","requestType":"GET","requestContentType":"application/x-www-form-urlencoded","headerFillRules":[],"inputFillRules":[{"source":"env_userId","sourceType":"VARIABLE","sourceDataType":null,"target":"userId","targetType":"INPUT_PARAM","targetDataType":{"type":"Integer"}}],"outputFillRules":[{"source":"age","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"Integer"},"target":"output_age","targetType":"VARIABLE","targetDataType":{"type":"Integer"}}]}},{"key":"condition_pq6w963k","name":"判断节点","elementType":"CONDITION","desc":"","incomings":["method_98a23hlu"],"outgoings":["end_KqdO1w6v"],"conditions":[{"conditionName":"存款小于10万","conditionType":"CUSTOM","outgoing":"method_2w5t1jbt","expression":"input_deposit<=100000","conditionExpressions":[[{"envKey":"input_deposit","dataType":{"type":"Double","itemType":""},"operator":"lessThanOrEqual","assignType":"CONSTANT","value":100000}]]},{"conditionName":"存款大于10万","conditionType":"CUSTOM","outgoing":"method_wt93gori","expression":"input_deposit>100000","conditionExpressions":[[{"envKey":"input_deposit","dataType":{"type":"Double","itemType":""},"operator":"greaterThan","assignType":"CONSTANT","value":100000}]]},{"conditionName":"默认else分支","conditionType":"DEFAULT","outgoing":"end_KqdO1w6v","expression":"","conditionExpressions":[]}]},{"key":"method_2w5t1jbt","name":"送10元话费","elementType":"METHOD","desc":"","incomings":["condition_pq6w963k"],"outgoings":["end_KqdO1w6v"],"method":{"suiteId":1,"methodId":15,"url":"http://127.0.0.1:9127/example/order/placeOrder","requestType":"POST","requestContentType":"application/x-www-form-urlencoded","headerFillRules":[],"inputFillRules":[{"source":"env_userId","sourceType":"VARIABLE","sourceDataType":null,"target":"userId","targetType":"INPUT_PARAM","targetDataType":{"type":"Integer"}},{"source":"送10元话费","sourceType":"CONSTANT","sourceDataType":null,"target":"orderName","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}}],"outputFillRules":[{"source":"orderName","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"String"},"target":"output_orderName","targetType":"VARIABLE","targetDataType":{"type":"String"}}]}},{"key":"method_wt93gori","name":"送一双耐克的鞋","elementType":"METHOD","desc":"","incomings":["condition_pq6w963k"],"outgoings":["end_KqdO1w6v"],"method":{"suiteId":1,"methodId":15,"url":"http://127.0.0.1:9127/example/order/placeOrder","requestType":"POST","requestContentType":"application/x-www-form-urlencoded","headerFillRules":[],"inputFillRules":[{"source":"env_userId","sourceType":"VARIABLE","sourceDataType":null,"target":"userId","targetType":"INPUT_PARAM","targetDataType":{"type":"Integer"}},{"source":"送一双耐克的鞋","sourceType":"CONSTANT","sourceDataType":null,"target":"orderName","targetType":"INPUT_PARAM","targetDataType":{"type":"String"}}],"outputFillRules":[{"source":"orderName","sourceType":"OUTPUT_PARAM","sourceDataType":{"type":"String"},"target":"output_orderName","targetType":"VARIABLE","targetDataType":{"type":"String"}}]}}]', '[{"key":"userName","name":"用户名称","dataType":{"type":"String"},"required":false},{"key":"password","name":"密码","dataType":{"type":"String"},"required":false},{"key":"deposit","name":"存款","dataType":{"type":"Double","itemType":""},"required":false}]', '[{"key":"userName","name":"用户名称","dataType":{"type":"String"}},{"key":"age","name":"用户年龄","dataType":{"type":"Integer"}},{"key":"orderName","name":"订单名称","dataType":{"type":"String"}}]', '[{"key":"env_isLogin","name":"是否登录成功变量","dataType":{"type":"Boolean"}},{"key":"env_userId","name":"用户ID变量","dataType":{"type":"Integer"}},{"key":"input_userName","name":"用户名称","dataType":{"type":"String"}},{"key":"input_password","name":"密码","dataType":{"type":"String"}},{"key":"input_deposit","name":"存款","dataType":{"type":"Double","itemType":""}},{"key":"output_userName","name":"用户名称","dataType":{"type":"String"}},{"key":"output_age","name":"用户年龄","dataType":{"type":"Integer"}},{"key":"output_orderName","name":"订单名称","dataType":{"type":"String"}}]', 0, '2024-07-04 00:09:26', 1, '2024-07-04 00:10:44', NULL);
