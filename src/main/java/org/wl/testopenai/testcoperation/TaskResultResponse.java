package org.wl.testopenai.testcoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class TaskResultResponse {

    private String result;

    @JsonPropertyDescription("返回处理结果")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
