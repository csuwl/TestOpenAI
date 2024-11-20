package org.wl.testopenai.testcoperation;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class SqlRequest {
    @JsonPropertyDescription("需要执行的sql")
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
