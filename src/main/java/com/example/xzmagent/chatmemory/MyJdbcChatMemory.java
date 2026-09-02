package com.example.xzmagent.chatmemory;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.xzmagent.converter.MessageConverter;
import com.example.xzmagent.domain.ChatHistoryDO;
import com.example.xzmagent.repository.mysql.ChatHistoryMapper;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
public class MyJdbcChatMemory implements ChatMemory {
    @Resource
    private ChatHistoryMapper chatHistoryMapper;

    @Override
    public void add(String conversationId, List<Message> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }
        for (Message message : messages) {
            ChatHistoryDO chatMessage = MessageConverter.toChatMessage(message, conversationId);

            chatHistoryMapper.insert(chatMessage);
        }


    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        LambdaQueryWrapper<ChatHistoryDO> queryWrapper = new LambdaQueryWrapper<>();
        // 查询最近的 lastN 条消息
        queryWrapper.eq(ChatHistoryDO::getConversationId, conversationId)
                .orderByDesc(ChatHistoryDO::getCreateTime)
                .last(lastN > 0, "LIMIT " + lastN);
        // 查询最近lastN条记录
        List<ChatHistoryDO> doList = chatHistoryMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(doList)) {
            Collections.reverse(doList);
        }
        return doList.stream().map(MessageConverter::toMessage).toList();
    }



    @Override
    public void clear(String conversationId) {
        LambdaQueryWrapper<ChatHistoryDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatHistoryDO::getConversationId, conversationId);
        chatHistoryMapper.delete(queryWrapper);
    }
}
