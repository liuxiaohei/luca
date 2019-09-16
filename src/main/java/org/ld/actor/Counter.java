package org.ld.actor;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class Counter extends AbstractLoggingActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(User.class, e -> getSender().tell("成功收到用户消息!", ActorRef.noSender()))
                .matchAny(messages -> log.info("UserLoginActor 接收到消息:{}", messages))
                .build();    }


    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("actorSystemName");
        ActorRef userLoginActor = actorSystem.actorOf(Props.create(Counter.class, Counter::new), "userLoginActorName");
        userLoginActor.tell(new User(1, "Dong", 30), ActorRef.noSender());
        userLoginActor.tell("哇", ActorRef.noSender());
        actorSystem.terminate(); // 这个方法终止 actor
    }
}
