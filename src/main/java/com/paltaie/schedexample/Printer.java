package com.paltaie.schedexample;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Printer extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Print.class, p -> log.info(p.what))
                .build();
    }

    public static class Print {
        private final String what;

        public Print(String what) {
            this.what = what;
        }
    }
}
