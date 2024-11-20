package org.wl.testopenai.function;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StrategyResponse {

    @JsonProperty("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
