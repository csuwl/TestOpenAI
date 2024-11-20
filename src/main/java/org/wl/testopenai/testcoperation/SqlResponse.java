package org.wl.testopenai.testcoperation;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class SqlResponse {

    @JsonPropertyDescription("sql执行结果")
    private String sqlResult;

    public String getSqlResult() {
        return sqlResult;
    }

    public void setSqlResult(String sqlResult) {
        this.sqlResult = sqlResult;
    }
}
