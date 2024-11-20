package org.wl.testopenai;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.wl.testopenai.function.LocationService;
import org.wl.testopenai.function.TotalFunction;

@Component
@Configuration
public class BeanConfig {

    @Autowired
    LocationService locationService;
    @Autowired
    TotalFunction totalFunction;
    @Value("${spring.ai.ollama.base-url}")
    private String baseUrl;

    @Bean
    public FunctionCallback getLocationBack() {

        return FunctionCallbackWrapper.builder(locationService)
                .withName("locationServiceCallBack") // (1) function name
                .withDescription("获取用户的位置") // (2) function description
                .build();
    }
    @Bean
    public FunctionCallback getIntelligenceBack() {
        return FunctionCallbackWrapper.builder(totalFunction).withName("决定让哪个智能体做任务").withDescription("决定让哪个智能体做任务，输入智能体名称和任务。如果要获取位置可以输入 智能体名称：locationModel 和 任务：获取当前位置 参数").build();
    }

    @Bean("ollamaChatModel")
    public OllamaChatModel getOllamaChatModel() {
        OllamaChatModel chatModel = OllamaChatModel.builder()
                .withOllamaApi(new OllamaApi(baseUrl))
                .withDefaultOptions(OllamaOptions.builder()
                        .withModel("qwen2.5:7b")
                        .withTemperature(0.05)
                        .withKeepAlive("2h").build())
                .build();
        return chatModel;
    }

    @Bean("ollamaChatModelVision")
    public OllamaChatModel getOllamaChatModelVision() {
        OllamaChatModel chatModel = OllamaChatModel.builder()
                .withOllamaApi(new OllamaApi(baseUrl))
                .withDefaultOptions(OllamaOptions.builder()
                        .withModel("llama3.2-vision")
                        .withTemperature(0.05)
                        .withKeepAlive("2h").build())
                .build();
        return chatModel;
    }

    @Bean
    public DruidDataSource getDataSource() {
        // 数据库URL，通常是 jdbc:mysql://<hostname>:<port>/<database_name>
        String url = "jdbc:mysql://localhost:3306/aas";
        // 数据库用户名
        String user = "root";
        // 数据库密码
        String password = "123456";

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(10);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(60000);
        druidDataSource.setValidationQuery("select 1");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestOnReturn(true);
        druidDataSource.setQueryTimeout(60 * 60);

        return druidDataSource;
    }
}
