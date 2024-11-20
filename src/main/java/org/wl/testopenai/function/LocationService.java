package org.wl.testopenai.function;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * 获取测试信息
 */
@Component
@Description("获取用户的位置")
public class LocationService implements Function<Request, Response> {


    @Override
    public Response apply(Request request) {
        Response response = new Response();
        response.setLocation("中国广东深圳");
        return response;
    }
}
