package com.example.xzmagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = {com.alibaba.cloud.ai.autoconfigure.dashscope.DashScopeAutoConfiguration.class,
        org.springframework.ai.autoconfigure.mcp.client.McpClientAutoConfiguration.class})
public class XzmAgentApplication {

    public static void main(String[] args) {

        SpringApplication.run(XzmAgentApplication.class, args);
    }

}
