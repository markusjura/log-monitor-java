import akka.actor.ActorRef;
import akka.actor.Cancellable;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import services.LogProtocol;
import services.LogService;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    Optional<Cancellable> logScheduler = Optional.empty();

    @Override
    public void onStart(Application app) {
        super.onStart(app);

        logScheduler();
    }

    private Cancellable logScheduler() {
        return Akka.system().scheduler().schedule(
                Duration.Zero(),
                Duration.create(1000, TimeUnit.MILLISECONDS),
                LogService.instance(),
                LogProtocol.latest,
                Akka.system().dispatcher(),
                ActorRef.noSender()
        );
    }

    @Override
    public void onStop(Application app) {
        super.onStop(app);

        logScheduler.ifPresent(Cancellable::cancel);
    }
}
