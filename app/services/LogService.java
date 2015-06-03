package services;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;
import models.LogMessage;
import play.Logger;
import play.libs.Akka;
import utils.LogGenerator;
import java.util.Deque;
import java.util.HashSet;

public class LogService extends AbstractActor {
    private static final ActorRef ref = Akka.system().actorOf(Props.create(LogService.class), "logService");

    public static ActorRef instance() {
        return ref;
    }

    public LogService() {
        this(new LogGenerator());
    }

    public LogService(LogGenerator logGenerator) {

        receive(ReceiveBuilder
            .match(LogProtocol.Latest.class, update -> {
                LogMessage newMessage = logGenerator.newMessage();
                Logger.debug("New message: " + newMessage.message);
            })
        .build());
    }
}
