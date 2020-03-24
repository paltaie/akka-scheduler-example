package com.paltaie.schedexample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;

public class Runner {

    private static ActorSystem actorSystem = ActorSystem.create();

    public static void main(String[] args) {
        ActorRef printer = actorSystem.actorOf(Props.create(Printer.class, (Creator<Printer>) Printer::new));
        actorSystem.actorOf(Props.create(ScheduleManager.class, (Creator<ScheduleManager>) () -> new ScheduleManager(printer)), "scheduleManager");
    }
}
