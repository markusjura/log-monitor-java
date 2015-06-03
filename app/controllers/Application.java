package controllers;

import akka.actor.ActorRef;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Akka;
import play.mvc.*;
import services.LogProtocol;
import services.LogService;
import services.UserService;
import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }

    public WebSocket<JsonNode> streamLogs() {
        return WebSocket.whenReady((in, out) -> {

            // Create new UserService and stream the current log history
            final ActorRef user = Akka.system().actorOf(Props.create(UserService.class, out));
            LogService.instance().tell(new LogProtocol.Watch(), user);

            // on close, tell the userActor to shutdown
            in.onClose(() -> {
                LogService.instance().tell(new LogProtocol.Unwatch(), user);
            });
        });
    }
}
