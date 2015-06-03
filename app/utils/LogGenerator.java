package utils;

import models.LogMessage;

import java.util.*;

public class LogGenerator {

    public LogMessage newMessage() {
        int randomIndex = new Random().nextInt(messages.size());
        return messages.get(randomIndex);
    }

    public static Deque<LogMessage> history(int length) {
        LogGenerator logGenerator = new LogGenerator();
        Deque<LogMessage> lastMessages = new LinkedList<LogMessage>();
        for (int i = 0; i < length; i++) {
            lastMessages.add(logGenerator.newMessage());
        }

        return lastMessages;
    }

    private final List<LogMessage> messages = new ArrayList<LogMessage>() {{
        add(new LogMessage("Set current project to log-monitor-java", "info"));
        add(new LogMessage("Loading project definition from ..", "info"));
        add(new LogMessage("Loading global plugins from /Users/mj/.sbt/0.13/plugins", "info"));
        add(new LogMessage("Done updating.", "info"));
        add(new LogMessage("Resolving org.fusesource.jansi#jansi;1.4 ...", "info"));
        add(new LogMessage("Updating {file:/Users/mj/.sbt/0.13/plugins/}global-plugins...", "info"));
        add(new LogMessage("Cluster Node [akka.tcp://application@127.0.0.1:2553] - Started up successfully", "info"));
        add(new LogMessage("Cluster Node [akka.tcp://application@127.0.0.1:2553] - Registered cluster JMX MBean [akka:type=Cluster]", "info"));
        add(new LogMessage("Remoting started; listening on addresses :[akka.tcp://application@127.0.0.1:2553]", "info"));
        add(new LogMessage("Starting remoting", "info"));
        add(new LogMessage("Running journal.SharedJournalApp 2553", "info"));
        add(new LogMessage("there was one feature warning; re-run with -feature for details", "warn"));
        add(new LogMessage("Compiling 1 Scala source and 1 Java source to /Users/mj/workspace/reactive-stocks-java8/backend/target/scala-2.11/classes...", "info"));
        add(new LogMessage("Service http://google.com is unreachable.", "error"));
        add(new LogMessage("tried http://repo.typesafe.com/typesafe/releases/xalan/serializer/2.7.1/serializer-2.7.1-javadoc.jar", "warn"));
        add(new LogMessage("Service http://twitter.com is unreachable.", "error"));
        add(new LogMessage("Service http://microsoft.com is unreachable.", "error"));
        add(new LogMessage("/Users/mj/workspace/log-monitor-java/app/services/LogService.java:14: illegal start of expression", "error"));
        add(new LogMessage("(compile:compileIncremental) javac returned nonzero exit code", "error"));
        add(new LogMessage("Loading project definition from ..", "info"));
    }};
}
