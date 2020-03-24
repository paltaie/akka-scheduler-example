package com.paltaie.schedexample;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;

import java.time.Duration;
import java.time.LocalTime;

public class ScheduleManager extends AbstractActor {

    private final Config config;
    private final LoggingAdapter log;
    private final ActorRef printer;

    public ScheduleManager(ActorRef printer) {
        this.log = Logging.getLogger(this);
        this.printer = printer;
        this.config = context().system().settings().config();
    }

    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }

    @Override
    public void preStart() {
        config.getConfigList("jobs")
                .stream()
                .map(Job::parse)
                .peek(job -> log.info("Scheduling {} to run at {} and repeat every {}", job.getName(), job.getStartTime(), job.getInterval()))
                .forEach(job ->
                    getContext().system().getScheduler().scheduleAtFixedRate(Duration.between(LocalTime.now(), job.getStartTime()),
                            job.getInterval(),
                            printer,
                            new Printer.Print(job.getThingToPrint()),
                            getContext().system().dispatcher(),
                            self()
                    )
        );
    }
}
