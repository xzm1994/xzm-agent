package com.example.xzmagent.converter;

import com.example.xzmagent.domain.ChatHistoryDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.messages.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MessageConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将 Message 转换为 ChatMessage
     */
    public static ChatHistoryDO toChatMessage(Message message, String conversationId){
        try{

            return ChatHistoryDO.builder()
                    .conversationId(conversationId)
                    .messageType(message.getMessageType())
                    .content(message.getText())
                    .metadata(objectMapper.writeValueAsString(message.getMetadata()))
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
        } catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 将 ChatMessage 转换为 Message
     */
    public static Message toMessage(ChatHistoryDO chatMessage) {
        MessageType messageType = chatMessage.getMessageType();
        String text = chatMessage.getContent();
        Map<String,Object> metadata;
        try{

            metadata = objectMapper.readValue(chatMessage.getMetadata(), new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return switch (messageType) {
            case USER -> new UserMessage(text);
            case ASSISTANT -> new AssistantMessage(text, metadata);
            case SYSTEM -> new SystemMessage(text);
            case TOOL -> new ToolResponseMessage(List.of(), metadata);
        };
    }

}
