package org.ld.actors;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.ld.beans.User;


public class Counter extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(User.class, e -> getSender().tell("成功收到用户消息!", ActorRef.noSender()))
                .matchAny(messages -> log.info("UserLoginActor 接收到消息:{}", messages))
                .build();
    }


    public static void main(String[] args) throws Exception {
        ActorSystem actorSystem = ActorSystem.create("actorSystemName");
        ActorRef userLoginActor = actorSystem.actorOf(Props.create(Counter.class, Counter::new), "userLoginActorName");
        userLoginActor.tell(new User(1, "Dong", 30), ActorRef.noSender());
        userLoginActor.tell("哇", ActorRef.noSender());
        Thread.sleep(10);
        actorSystem.terminate(); // 这个方法终止 actor
    }
}
