package org.wl.testopenai.function;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

/**
 *
 */
@Component
public class BaiduService implements Function<BaiduRequest, BaiduResponse> {
    @Override
    public BaiduResponse apply(BaiduRequest baiduRequest) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpResponse<String> response=null;
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://baidu.com/s?wd=" + URLEncoder.encode(baiduRequest.getSearchStr(), "UTF-8")))
                    .GET()
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers. ofString());

            System.out.println(response.body());

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        BaiduResponse baiduResponse = new BaiduResponse();
        baiduResponse.setResult(response.body().toString());
        return baiduResponse;
    }
}
