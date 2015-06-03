package services;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;
import models.LogMessage;
import play.libs.Akka;
import utils.LogGenerator;
import java.util.Deque;
import java.util.HashSet;

public class LogService extends AbstractActor {

    private static final ActorRef ref = Akka.system().actorOf(Props.create(LogService.class), "logService");

    public static ActorRef instance() {
        return ref;
    }

    final HashSet<ActorRef> watchers = new HashSet<ActorRef>();
    final Deque<LogMessage> logHistory = LogGenerator.history(10);

    public LogService() {
        this(new LogGenerator());
    }

    public LogService(LogGenerator logGenerator) {

        receive(ReceiveBuilder
                .match(LogProtocol.Latest.class, update -> {
                    LogMessage newMessage = logGenerator.newMessage();
                    logHistory.add(newMessage);
                    logHistory.remove();
                    watchers.forEach(watcher -> watcher.tell(new LogProtocol.Update(newMessage), self()));
                })

                .match(LogProtocol.Watch.class, watch -> {
                    sender().tell(new LogProtocol.History(logHistory), self());
                    watchers.add(sender());
                })

                .match(LogProtocol.Unwatch.class, unwatch -> {
                    watchers.remove(sender());
                })
                .build());
    }
}
