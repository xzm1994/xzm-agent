package com.example.xzmagent.config;

import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DeepSeekConfig {
    @Value("${spring.ai.openai.embedding.api-key}") // 注意！少了${}占位符！！
    private String apiKey_token;

    @Bean
    @Primary
    public EmbeddingModel dashscopeEmbeddingModel() {
        String apiKey = apiKey_token;
        String baseUrl = "https://dashscope.aliyuncs.com/compatible-mode/v1";

        OpenAiApi openAiApi = OpenAiApi.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .embeddingsPath("/embeddings")
                .build();
        OpenAiEmbeddingOptions openAiEmbeddingOptions = OpenAiEmbeddingOptions.builder().model("text-embedding-v4").build();
        return new OpenAiEmbeddingModel(openAiApi, MetadataMode.EMBED, openAiEmbeddingOptions);
    }
}