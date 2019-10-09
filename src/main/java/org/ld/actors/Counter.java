package org.ld.actors;

import akka.actor.*;
import org.ld.utils.Logger;


public class Counter extends AbstractActor {

    private static Logger log = Logger.newInstance();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, e -> getSender().tell("成功收到用户消息!", ActorRef.noSender()))
                .matchAny(messages -> log.info(messages::toString))
                .build();
    }


    public static void main(String[] args) throws Exception {
        ActorSystem actorSystem = ActorSystem.create("actorSystemName");
        ActorRef userLoginActor = actorSystem.actorOf(Props.create(Counter.class, Counter::new), "userLoginActorName");
        userLoginActor.tell(666, ActorRef.noSender());
        Thread.sleep(10);
        actorSystem.terminate(); // 这个方法终止 actor
    }
}
