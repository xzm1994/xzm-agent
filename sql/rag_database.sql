CREATE TABLE `chat_history` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                `conversation_id` varchar(255) NOT NULL COMMENT '会话ID',
                                `content` text COMMENT '消息内容',
                                `message_type` varchar(50) COMMENT '消息类型(USER,ASSISTANT,SYSTEM,TOOL)',
                                `metadata` text COMMENT '元数据json字符串',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `update_time` datetime DEFAULT NULL COMMENT '更新时间,乐观锁版本',
                                `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
                                PRIMARY KEY (`id`),
                                KEY `idx_conversation_id` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI聊天历史表';
