package org.wl.testopenai.function;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StrategyRequest {
    @JsonProperty("task")
    private String task;

    @JsonProperty("intelligence")
    private String intelligence;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }
}
