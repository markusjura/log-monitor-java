package services;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.LogMessage;
import play.Logger;
import play.libs.Json;
import play.mvc.WebSocket;

public class UserService extends AbstractActor {

    public UserService(WebSocket.Out<JsonNode> out) {
        JsonNode json = Json.newObject()
            .put("type", "logupdate")
            .put("message", "Some log message")
            .put("level", "info");

        out.write(json);

        receive(ReceiveBuilder
            .match(Object.class, any -> {
                Logger.debug("Received messages: " + any);
            })
            .build());
    }
}
