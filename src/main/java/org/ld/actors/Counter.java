package org.ld.actors;

import akka.actor.*;
import org.ld.utils.Logger;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
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
        //ForkJoinPool pool = new ForkJoinPool(2000);
        log.info(() -> "start");
        ActorSystem actorSystem = ActorSystem.create("actorSystemName");
        List<ActorRef> list = IntStream.rangeClosed(0, 1000000).boxed().map(e -> actorSystem.actorOf(Props.create(Counter.class, Counter::new), "userLoginActorName" + e)).collect(Collectors.toList());
        long time = System.currentTimeMillis();
        //list.forEach(userLoginActor ->userLoginActor.tell(new User(1, "User" , 30), ActorRef.noSender()));
        IntStream.rangeClosed(0, 1000000).parallel().boxed().forEach(e -> log.info(() -> "成功收到用户消息!" + e ));
        long endtime = System.currentTimeMillis() - time;
        Thread.sleep(10000);
        actorSystem.terminate(); // 这个方法终止 actor
        log.info(() -> "" + endtime);
    }
}
