package org.wl.testopenai.transfer;

import org.springframework.ai.agent.Agent;
import org.springframework.ai.chat.messages.InstructionMessage;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.wl.testopenai.function.Request;

import java.util.ArrayList;
import java.util.function.Function;

@Component
public class TransferComponent {
    @Autowired
    @Qualifier("ollamaChatModel")
    private OllamaChatModel ollamaChatModel;

    public void test() {
//        Agent agentA = new Agent("agentA", new InstructionMessage("You are an intelligent butler, skilled at getting professionals to do professional things. by the way agentB skilled in singing"),
//                ollamaChatModel);
//
//        Agent agentB = new Agent("agentB", new InstructionMessage("You are a singer, skilled in singing"),
//                ollamaChatModel);
//
//        ArrayList<FunctionCallback> functionCallbacks = new ArrayList<>();
//        functionCallbacks.add(FunctionCallbackWrapper.builder(new Function<Request, Agent>() {
//                    @Override
//                    public Agent apply(Request request) {
//                        return agentB;
//                    }
//                }).withDescription("You can call this function to sing")
//                .withName("assign task to agentB")
//                .build());
//        agentA.setFunctionCallbacks(functionCallbacks);
//
//        String response = agentA.call("Hello, sing me a song");
//        System.out.println(response);
    }


    public void test2() {
//        Agent agentA = new Agent("agentA", new InstructionMessage("你是一个智能管家，擅长把活丢给其它人解决。比如让擅长唱歌的人去唱歌"),
//                ollamaChatModel);
//
//        Agent agentB = new Agent("agentB", new InstructionMessage("你是一个歌唱家，擅长唱歌"),
//                ollamaChatModel);
//
//        ArrayList<FunctionCallback> functionCallbacks = new ArrayList<>();
//        functionCallbacks.add(FunctionCallbackWrapper.builder(new Function<String, Agent>() {
//                    @Override
//                    public Agent apply(String s) {
//                        return agentB;
//                    }
//                }).withDescription("调用此函数让别人帮你唱歌")
//                .withName("assign task to agentB")
//                .build());
//        agentA.setFunctionCallbacks(functionCallbacks);
//
//        String response = agentA.call("你好，唱首歌");
//        System.out.println(response);
    }
}
