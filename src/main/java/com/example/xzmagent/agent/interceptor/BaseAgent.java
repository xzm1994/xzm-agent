package com.example.xzmagent.agent.interceptor;

import com.example.xzmagent.agent.enums.AgentState;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
@Slf4j
public abstract class BaseAgent {

    private String name;

    // 提示
    private String systemPrompt;
    private String nextStepPrompt;

    //状态
    private AgentState state =  AgentState.IDLE;

    // 执行控制
    private int maxSteps = 10;
    private int currentStep = 0;

    // LLm
    private ChatClient chatClient;

    // memory（想要自主维护绘画上下文）
    private List<Message> messageList = new ArrayList<>();

    public String run(String userPrompt){
        if (this.state != AgentState.IDLE) {
            throw new RuntimeException("不能运行agent，当前状态为：" + this.state);
        }
        if (StringUtils.isBlank(userPrompt)) {
            throw new RuntimeException("不能运行agent，userPrompt为空");
        }
        this.state = AgentState.RUNNING;
        messageList.add(new UserMessage(userPrompt));
        List<String> results = new ArrayList<>();
        try{
            // 执行循环
            for (int i = 0; i < maxSteps && state!=AgentState.FINISHED; i++) {
                int stepNum = i + 1;
                currentStep = stepNum;
                log.info("currrent step {}/{}", stepNum, maxSteps);
                // 单步执行
                String stepResult = step();
                String result = "Step " + stepNum + ": " + stepResult;
                results.add(result);
            }
            // 检查是否超出步骤限制
            if (currentStep >= maxSteps) {
                state = AgentState.FINISHED;
                results.add("Terminated: Reached max steps (" + maxSteps + ")");
            }
            return String.join("\n", results);
        } catch (Exception e) {
            state = AgentState.ERROR;
            log.error("error executing agent", e);
            return "执行错误" + e.getMessage();
        } finally {
            // 3、清理资源
            this.cleanup();
        }
    }


    public SseEmitter runASync(String userPrompt){
        SseEmitter sseEmitter = new SseEmitter(300000L);
        CompletableFuture.runAsync(() -> {
            try {
                if (this.state != AgentState.IDLE) {
                    sseEmitter.send("不能运行agent，当前状态为：" + this.state);
                    sseEmitter.complete();
                    return;
                }
                if (StringUtils.isBlank(userPrompt)) {
                    sseEmitter.send("不能运行agent，userPrompt为空");
                    sseEmitter.complete();
                    return;
                }
            } catch (Exception e) {
                sseEmitter.completeWithError(e);
            }
            this.state = AgentState.RUNNING;
            messageList.add(new UserMessage(userPrompt));
            List<String> results = new ArrayList<>();
            try{
                // 执行循环
                for (int i = 0; i < maxSteps && state!=AgentState.FINISHED; i++) {
                    int stepNum = i + 1;
                    currentStep = stepNum;
                    log.info("currrent step {}/{}", stepNum, maxSteps);
                    // 单步执行
                    String stepResult = step();
                    String result = "Step " + stepNum + ": " + stepResult;
                    results.add(result);
                    sseEmitter.send(result);
                }
                // 检查是否超出步骤限制
                if (currentStep >= maxSteps) {
                    state = AgentState.FINISHED;
                    results.add("Terminated: Reached max steps (" + maxSteps + ")");
                    sseEmitter.send("Terminated: Reached max steps (" + maxSteps + ")");
                }
                sseEmitter.complete();
            } catch (Exception e) {
                state = AgentState.ERROR;
                log.error("error executing agent", e);
                try {
                    sseEmitter.send("执行错误 + e.getMessage()");
                    sseEmitter.complete();
                } catch (IOException ex) {
                    sseEmitter.completeWithError(e);
                }
            } finally {
                // 3、清理资源
                this.cleanup();
            }
        });
        // 设置超时和完成回调
        sseEmitter.onTimeout(() -> {
            this.state =AgentState.ERROR;
            this.cleanup();
            log.warn("sseEmitter timeout");
        });

        sseEmitter.onCompletion(() -> {
            if (this.state == AgentState.RUNNING){
                this.state = AgentState.FINISHED;
            }
            this.cleanup();
            log.info("sseEmitter completed");
        });
        return sseEmitter;
    }


    /**
     * 定义单个步骤
     *
     * @return
     */
    public abstract String step();

    /**
     * 清理资源
     */
    protected void cleanup() {
        // 子类可以重写此方法来清理资源
    }
}
