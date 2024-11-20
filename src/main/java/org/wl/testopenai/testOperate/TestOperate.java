package org.wl.testopenai.testOperate;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.imageio.ImageReader;
import java.awt.*;

@Component
public class TestOperate {
    @Autowired
    @Qualifier("ollamaChatModel")
    private OllamaChatModel ollamaChatModel;
    @Autowired
    @Qualifier("ollamaChatModelVision")
    private OllamaChatModel ollamaChatModelVision;

    public void testOperate() {

//        String call = ollamaChatModel.call("你好");
//        System.out.println(call);

        FileSystemResource imageResource = new FileSystemResource("./picture0.jpg");

        UserMessage userMessage = new UserMessage("屏幕上显示了什么内容？",
                new Media(MimeTypeUtils.IMAGE_JPEG, imageResource));

        ChatResponse chatResponse = ollamaChatModelVision.call(new Prompt(userMessage));

        System.out.println(chatResponse);

    }
}
