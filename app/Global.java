import akka.actor.ActorRef;
import akka.actor.Cancellable;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import services.LogProtocol;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    Optional<Cancellable> logScheduler = Optional.empty();

    @Override
    public void onStart(Application app) {
        super.onStart(app);
    }

    @Override
    public void onStop(Application app) {
        super.onStop(app);
    }
}
