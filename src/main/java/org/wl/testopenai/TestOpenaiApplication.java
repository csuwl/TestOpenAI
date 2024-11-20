package org.wl.testopenai;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import io.netty.buffer.ByteBuf;

import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.wl.testopenai.testOperate.TestOperate;
import org.wl.testopenai.testcoperation.SqlTestComponent;
import org.wl.testopenai.transfer.TransferComponent;
import reactor.netty.http.client.HttpClientRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.Flow;

@SpringBootApplication
public class TestOpenaiApplication {

    public static TransferComponent transferComponent;

    public static SqlTestComponent sqlTestComponent;

    public static TestOperate testOperate;

    @Autowired
    public void setTransferComponent(TransferComponent transferComponent) {
        TestOpenaiApplication.transferComponent = transferComponent;
    }

    @Autowired
    public void setSqlTestComponent(SqlTestComponent sqlTestComponent) {
        TestOpenaiApplication.sqlTestComponent = sqlTestComponent;
    }

    @Autowired
    public void setTestOperate(TestOperate testOperate) {
        TestOpenaiApplication.testOperate = testOperate;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TestOpenaiApplication.class, args);
        testOperate.testOperate();

//        FileInputStream fileInputStream = new FileInputStream("./ec06eeb8011e4305963353038544e378.jpeg");
//        byte[] bytes = fileInputStream.readAllBytes();
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("model","llama3.2-vision");
//        JSONArray messageArray = new JSONArray();
//        JSONObject userMessage = new JSONObject();
//        userMessage.put("role","user");
//        userMessage.put("content","图片内容是什么？");
//        JSONArray jsonImageArray = new JSONArray();
//
//        jsonImageArray.add(Base64.getEncoder().encodeToString(bytes));
//        userMessage.put("images",jsonImageArray);
//        messageArray.add(userMessage);
//        jsonObject.put("messages",messageArray );
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:11434/v1/chat/completions"))
//                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toJSONString()))
//                .build();
//        try {
//            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//            System.out.println(httpResponse);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }


}
