package com.paltaie.schedexample;

import com.typesafe.config.Config;

import java.time.Duration;
import java.time.LocalTime;

public class Job {
    private final String name;
    private final LocalTime startTime;
    private final Duration interval;
    private final String thingToPrint;

    public Job(String name, LocalTime startTime, Duration interval, String thingToPrint) {
        this.name = name;
        this.startTime = startTime;
        this.interval = interval;
        this.thingToPrint = thingToPrint;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Duration getInterval() {
        return interval;
    }

    public String getThingToPrint() {
        return thingToPrint;
    }

    public static Job parse(Config config) {
        return new Job(config.getString("name"), LocalTime.parse(config.getString("start-time")), config.getDuration("interval"), config.getString("thing-to-print"));
    }
}