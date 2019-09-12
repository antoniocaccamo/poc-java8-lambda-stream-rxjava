package com.linkedin.fullappakkaspring.actors;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.time.Duration;

public class Poller extends AbstractActorWithTimers {

    private static final Object TICK_KEY = "TickKey";
    private final ActorRef requestorActor;
    private final String cryptoName;

    public Poller(ActorRef requestorActor, String cryptoName) {
        this.requestorActor = requestorActor;
        this.cryptoName = cryptoName;
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(3000));
    }

    public static Props props(String cryptoName, ActorRef requestorActor) {
        return Props.create(Poller.class, () -> new Poller(requestorActor, cryptoName));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                FirstTick.class, message -> {
                    getTimers()
                            .startPeriodicTimer(TICK_KEY,
                                    new Tick(), Duration.ofSeconds(3));
                }).match(
                     Tick.class, message ->
                        requestorActor.tell(new PriceRequestor.GetThisCryptoPrice(cryptoName),getSelf())
        ).build();

    }

    private static final class FirstTick {

    }

    private static final class Tick {

    }
}
