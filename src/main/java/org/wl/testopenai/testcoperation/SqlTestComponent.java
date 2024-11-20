package org.wl.testopenai.testcoperation;

import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.pool.DruidPooledConnection;
//import org.springframework.ai.agent.Agent;
//import org.springframework.ai.chat.messages.InstructionMessage;
//import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.wl.testopenai.function.Request;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class SqlTestComponent {
    @Autowired
    private OllamaChatModel ollamaChatModel;
    @Autowired
    private DruidDataSource druidDataSource;


//    public void test() {
//        Agent agentA = new Agent("总管", new InstructionMessage("你是一个智能助理，可以帮我处理一些事物，如果遇到不懂的可以交给专业的人帮你解决"), ollamaChatModel);
//        Agent agentB = new Agent("sql类的问题", new InstructionMessage("你是一个专门处理本地mysql数据库问题的助理，可以帮我查询一些本地数据"), ollamaChatModel);
//
//        agentA.setFunctionCallbacks(List.of(FunctionCallbackWrapper.builder(new Function<Request, Agent>() {
//                    @Override
//                    public Agent apply(Request request) {
//                        return agentB;
//                    }
//                }).withName("查询数据库类的问题交给它处理")
//                .withDescription("查询数据库类的问题交给它处理")
//                .build()));
//
//        agentB.setFunctionCallbacks(List.of(FunctionCallbackWrapper.builder(new Function<SqlRequest, SqlResponse>() {
//                    @Override
//                    public SqlResponse apply(SqlRequest sqlRequest) {
//                        try {
//                            DruidPooledConnection connection = druidDataSource.getConnection();
//                            Statement statement = connection.createStatement();
//                            ResultSet resultSet = statement.executeQuery(sqlRequest.getSql());
//                            ResultSetMetaData metaData = resultSet.getMetaData();
//                            int columnCount = metaData.getColumnCount();
//                            StringBuilder stringBuilder = new StringBuilder();
//
//                            for (int i = 1; i <= columnCount; i++) {
//                                String columnName = metaData.getColumnName(i);
//                                stringBuilder.append(columnName);
//                                stringBuilder.append("|");
//                            }
//                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//                            stringBuilder.append("\n");
//
//                            while (resultSet.next()) {
//                                for (int i = 1; i <= columnCount; i++) {
//                                    String columnResult = resultSet.getString(i);
//                                    stringBuilder.append(columnResult);
//                                    stringBuilder.append("|");
//                                }
//                                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//                                stringBuilder.append("\n");
//                            }
//                            statement.close();
//                            connection.close();
//                            SqlResponse sqlResponse = new SqlResponse();
//                            sqlResponse.setSqlResult(stringBuilder.toString());
//                            return sqlResponse;
//                        } catch (SQLException e) {
//                            System.out.println(e);
//                            SqlResponse sqlResponse = new SqlResponse();
//                            sqlResponse.setSqlResult(e.getMessage());
//                            return sqlResponse;
//                        }
//                    }
//                }).withName("查询sql")
//                .withDescription("传入sql可以直接查询本地mysql数据库")
//                .withInputType(SqlRequest.class)
//                .build()));
//
//
//
//        String result = agentA.call("我本地数据都有哪些表？");
//        System.out.println(result);
//        result = agentA.call("newspaper表里有多少条数据？");
//        System.out.println(result);
//    }


}
