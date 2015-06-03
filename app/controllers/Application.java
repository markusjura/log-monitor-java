package controllers;

import akka.actor.ActorRef;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Akka;
import play.mvc.*;
import services.LogProtocol;
import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }
}
