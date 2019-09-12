package com.linkedin.fullappakkaspring.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.linkedin.fullappakkaspring.service.CoinbaseService;

public class PriceRequestor extends AbstractActor {

    private final ActorRef printerActor;
    private final CoinbaseService coinbaseService;

    public PriceRequestor(ActorRef printerActor, CoinbaseService coinbaseService) {
        this.printerActor = printerActor;
        this.coinbaseService = coinbaseService;
    }

    public static Props props(ActorRef printerActor, CoinbaseService coinbaseService) {
        return Props.create(PriceRequestor.class,
                        () -> new PriceRequestor(printerActor, coinbaseService));
    }

    @Override
    public Receive createReceive() {

        return receiveBuilder().match(GetThisCryptoPrice.class, what ->
        printerActor
                .tell(new Printer
                        .CryptoPrice(coinbaseService
                        .getCryptoPrice(what.whatPrice)), getSelf())).build();
    }

    public static class GetThisCryptoPrice {
        public final String whatPrice;

        public GetThisCryptoPrice(String whatPrice) {
            this.whatPrice = whatPrice;
        }
    }
}
