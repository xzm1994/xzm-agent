//package com.example.xzmagent.advises;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.client.advisor.api.*;
//import org.springframework.ai.converter.BeanOutputConverter;
//import reactor.core.publisher.Flux;
//
//@RequiredArgsConstructor
//public class MyCustomAdvisor<T> implements CallAroundAdvisor, StreamAroundAdvisor {
//    private final BeanOutputConverter<T> converter;
//    @Override
//    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
//        // 1. 处理请求（前置处理）
//        AdvisedRequest modifiedRequest = processRequest(advisedRequest);
//
//        // 2. 调用链中的下一个Advisor
//        AdvisedResponse response = chain.nextAroundCall(modifiedRequest);
//
//        // 3. 处理响应（后置处理）
//        return response;
//    }
//
//
//    private AdvisedRequest processRequest(AdvisedRequest advisedRequest) {
//        System.out.println(advisedRequest.toString());
//        return advisedRequest;
//    }
//
//
//    @Override
//    public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
//        // 1. 处理请求
//        AdvisedRequest modifiedRequest = processRequest(advisedRequest);
//
//        // 2. 调用链中的下一个Advisor并处理流式响应
//        return chain.nextAroundStream(modifiedRequest)
//                .map(response -> processResponse(response));
//    }
//
//    private AdvisedResponse processResponse(AdvisedResponse response) {
//        return null;
//    }
//
//
//    @Override
//    public String getName() {
//        return "xzm自定义的Advisor";
//    }
//
//    @Override
//    public int getOrder() {
//        return 100;
//    }
//    // 实现方法...
//}
