package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogMessage {
    public String message;
    public String level;

    public LogMessage(String message, String level) {
        this.message = message;
        this.level = level;
    }
//
//    @JsonProperty("message")
//    public String getMessage() {
//        return message;
//    }
//
//    @JsonProperty("level")
//    public String getLevel() {
//        return level;
//    }
}
