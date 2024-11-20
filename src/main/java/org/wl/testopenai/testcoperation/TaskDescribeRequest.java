package org.wl.testopenai.testcoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class TaskDescribeRequest {

    private String taskDescribe;


    @JsonPropertyDescription("任务需求描述")
    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }
}
