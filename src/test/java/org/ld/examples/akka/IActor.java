package org.ld.examples.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.ld.actors.Other;
import org.ld.actors.User;
import org.ld.utils.Logger;

import java.util.stream.IntStream;

/**
 * https://www.wanaright.com/2018/09/08/akka-java8/
 */
public class IActor extends AbstractActor {

    private static Logger log = Logger.newInstance();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(messages -> log.info(messages::toString))
                .build();
    }
}
