package org.wl.testopenai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.wl.testopenai.function.BaiduRequest;
import org.wl.testopenai.function.BaiduResponse;
import org.wl.testopenai.function.BaiduService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class TestOllamaApplicationTests {

    @Value("${spring.ai.ollama.base-url}")
    private String baseUrl = null;

    @Autowired
    BeanConfig beanConfig;
    @Autowired
    BaiduService baiduService;


    @Test
    void contextLoads() throws IOException {
        ArrayList<FunctionCallback> functions = new ArrayList<>();
        functions.add(beanConfig.getLocationBack());

        ArrayList<FunctionCallback> totalFunctions = new ArrayList<>();
        functions.add(beanConfig.getIntelligenceBack());
//
//        OllamaChatModel assitentChatModel = new OllamaChatModel(new OllamaApi(baseUrl),
//                OllamaOptions.builder().withModel("llama3.2").build());



//        ChatResponse call = assitentChatModel.call(new Prompt(Arrays.asList(new UserMessage("你是一个管家智能体，根据用户的诉求解决用户的问题。你可以通过function call将任务交给其它智能体做。比如获取当前位置，可以使用locationModel智能体查询"),new UserMessage("你好，我在什么位置")), OllamaOptions.builder()
//                .withModel("llama3.2")
//                .withFunctionCallbacks(totalFunctions)
//                .build()));

//
//        System.out.println(call);

    }

    @Test
    void testBaidu() {
        BaiduRequest baiduRequest = new BaiduRequest();
        baiduRequest.setSearchStr("我的ip是多少");
        BaiduResponse response = baiduService.apply(baiduRequest);
        System.out.println(response);
    }

}
