package com.example.xzmagent.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xzmagent.domain.ChatHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对话历史Mapper
 */
@Mapper
public interface ChatHistoryMapper extends BaseMapper<ChatHistoryDO> {
//    /** 查询会话最近N条 */
//    List<ChatHistoryDO> listLatest(@Param("conversationId") String conversationId, @Param("lastN") int lastN);
//
//    void deleteByConversationId(@Param("conversationId") String conversationId);
}
