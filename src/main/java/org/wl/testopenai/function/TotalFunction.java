package org.wl.testopenai.function;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;
import org.wl.testopenai.BeanConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/**
 * 总的函数决策结构
 */
@Component
@Description("需要哪个智能体解决问题")
public class TotalFunction implements Function<StrategyRequest, StrategyResponse> {

    @Value("baseUrl")
    private String baseUrl;

//    private OllamaChatModel locationModel = new OllamaChatModel(new OllamaApi(baseUrl),
//            OllamaOptions.builder().withModel("llama3.2").build());

    private HashMap<String, ChatModel> registeredChatModel= new HashMap<>();

    @Autowired
    private LocationService locationService;

    @Override
    public StrategyResponse apply(StrategyRequest strategyRequest) {
//        registeredChatModel.put("locationModel",locationModel);
//
//        String intelligence = strategyRequest.getIntelligence();
//        String task = strategyRequest.getTask();
//
//        if(registeredChatModel.get(intelligence) == null){
//            StrategyResponse strategyResponse = new StrategyResponse();
//            strategyResponse.setResult("没有能帮助解决这个问题的其它智能体");
//            return strategyResponse;
//        }

//        ArrayList<FunctionCallback> functions = new ArrayList<>();
//        functions.add(FunctionCallbackWrapper
//                .builder(locationService)
//                .withName("locationService")
//                .withDescription("获取用户的位置").build());
//
//        OllamaChatModel ollamaChatModel = (OllamaChatModel) registeredChatModel.get(intelligence);
//        StrategyResponse strategyResponse = new StrategyResponse();
//        ChatResponse call = ollamaChatModel.call(new Prompt(task, OllamaOptions
//                .builder()
//                .withModel("llama3.2")
//                .withFunctionCallbacks(functions).build()));
//        strategyResponse.setResult(call.getResult().getOutput().toString());
//        return strategyResponse;

        return null;
    }
}
