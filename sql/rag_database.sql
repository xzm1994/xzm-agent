CREATE TABLE chat_history (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              conversation_id VARCHAR(128) NOT NULL COMMENT '会话id',
                              message_json TEXT NOT NULL COMMENT 'Message对象json',
                              create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                              INDEX idx_conv_id(conversation_id)
);