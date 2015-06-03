package services;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.LogMessage;
import play.libs.Json;
import play.mvc.WebSocket;

/**
 * The broker between the WebSocket and the LogService. The UserService holds the connection and sends serialized
 * JSON data to the client.
 */
public class UserService extends AbstractActor {

    public UserService(WebSocket.Out<JsonNode> out) {
        receive(ReceiveBuilder
                .match(LogProtocol.Update.class, logUpdate -> {
                    // Push message to client
                    JsonNode json = Json.newObject()
                        .put("type", "logupdate")
                        .put("message", logUpdate.event.message)
                        .put("level", logUpdate.event.level);
                    out.write(json);
                })

                .match(LogProtocol.History.class, logHistory -> {
                    // Push log history to client
                    ObjectNode json = Json.newObject()
                        .put("type", "loghistory");

                    ArrayNode historyJson = json.putArray("history");

                    for (LogMessage event : logHistory.history) {
                        ObjectNode jsonMessage = Json.newObject()
                            .put("message", event.message)
                            .put("level", event.level);
                        historyJson.add(jsonMessage);
                    }

                    out.write(json);
                })
                .build());
    }
}
