package com.example.xzmagent.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.MessageType;

import java.util.Date;
import java.util.Map;

@Data
@TableName("chat_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatHistoryDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "conversation_id")
    private String conversationId;
    /**
     * 消息内容
     */
    @TableField("content")
    private String content;
    @TableField("message_type")
    private MessageType messageType;

    @TableField(value = "metadata")
    private String metadata;
    /**
     * 创建时间
     */
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @Version
    @TableField(value = "`update_time`", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @TableField("`is_delete`")
    @TableLogic
    private boolean isDelete;

}
