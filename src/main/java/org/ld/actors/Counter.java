package org.ld.actors;

import akka.actor.*;
import org.ld.utils.Logger;

import java.util.stream.IntStream;

/**
 * https://www.wanaright.com/2018/09/08/akka-java8/
 */
public class Counter extends AbstractActor {

    private static Logger log = Logger.newInstance();

    private Integer i = 0;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(User.class, e -> log.info(() -> "成功收到用户消息!" + e.getUserName() + "-" + i++))
                .match(Other.class, e -> log.info(() -> "成功收到其它消息!" + e.getUserName() + "_" + i))
                .matchAny(messages -> log.info(messages::toString))
                .build();
    }


    public static void main(String[] args) throws InterruptedException {
        ActorSystem actorSystem = ActorSystem.create("actorSystemName");
        ActorRef userLoginActor = actorSystem.actorOf(Props.create(Counter.class, Counter::new), "userLoginActorName");
        IntStream.rangeClosed(0, 100).parallel().forEach(e -> {
                    userLoginActor.tell(new User(1, "Dong" + e, 30), ActorRef.noSender());
                    userLoginActor.tell(new User(1, "Dong" + e, 30), ActorRef.noSender());
                    try {
                        Thread.sleep(10);
                    } catch (Exception ignored) {
                    }
                }
        );
        Thread.sleep(1000);
        actorSystem.terminate(); // 这个方法终止 actor
    }
}
