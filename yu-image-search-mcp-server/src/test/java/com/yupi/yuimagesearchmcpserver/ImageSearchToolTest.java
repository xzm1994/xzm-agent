package com.yupi.yuimagesearchmcpserver;

import com.yupi.yuimagesearchmcpserver.tools.ImageSearchTool;
import com.yupi.yuimagesearchmcpserver.tools.WeatherTools;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageSearchToolTest {

    @Resource
    private WeatherTools weatherTools;

    @Test
    void searchImage() {
        String result = weatherTools.getWeather("上海");
        Assertions.assertNotNull(result);
    }
}
