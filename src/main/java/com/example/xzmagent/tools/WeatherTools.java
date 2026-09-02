package com.example.xzmagent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * @Description:
 * @ClassName: {@link WeatherTools}
 * @Author: XZM
 * @Date: 2026/9/2 22:02
 * @Version: 1.0.0
 */
public class WeatherTools {
    @Tool(description = "Get current weather for a location")
    public String getWeather(@ToolParam(description = "The city name") String city) {
        return "Current weather in " + city + ": Sunny, 25°C";
    }
}
