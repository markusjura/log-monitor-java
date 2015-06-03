package services;

import models.LogMessage;

import java.util.Deque;

public class LogProtocol {
    public static final class Latest {
        public Latest() {}
    }

    public static final Latest latest = new Latest();

    public static final class Update {
        public final LogMessage event;

        public Update(LogMessage event) {
            this.event = event;
        }
    }

    public static final class History {
        public final Deque<LogMessage> history;

        public History(Deque<LogMessage> history) {
            this.history = history;
        }
    }

    public static final class Watch {
        public Watch() {}
    }

    public static final class Unwatch {
        public Unwatch() {}
    }
}