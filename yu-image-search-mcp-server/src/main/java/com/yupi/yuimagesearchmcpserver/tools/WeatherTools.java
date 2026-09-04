package com.yupi.yuimagesearchmcpserver.tools;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @ClassName: {@link WeatherTools}
 * @Author: XZM
 * @Date: 2026/9/2 22:02
 * @Version: 1.0.0
 */
@Service
public class WeatherTools {
    @Tool(description = "Get current weather for a location")
    public String getWeather(@ToolParam(description = "The city name") String city) {
        return "Current weather in " + city + ": Sunny, 25°C";
    }
}
