package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogMessage {
    public String message;
    public String level;

    public LogMessage(String message, String level) {
        this.message = message;
        this.level = level;
    }
}
