package org.ld.examples.akka;

import akka.actor.AbstractActorWithStash;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.ld.utils.Logger;

public class MyActorWithStash extends AbstractActorWithStash {

    private static Logger log = Logger.newInstance();

    private int count = -1;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, s -> {
            if (count < 0) {
                sender().tell(s.length(),self());
            } else if (count == 2) {
                count = -1;
                unstashAll();
            } else {
                count += 1;
                stash();
            }
        }).build();
    }

    public static void main(String[] args) throws InterruptedException {
        log.info(() -> "start");
        ActorSystem actorSystem = ActorSystem.create("actorSystemName");
        ActorRef iactor = actorSystem.actorOf(Props.create(IActor.class,IActor::new),"iactor");
        ActorRef userLoginActor = actorSystem.actorOf(Props.create(MyActorWithStash.class, MyActorWithStash::new), "demo");
        userLoginActor.tell("aaa", iactor);
        Thread.sleep(10000);
        actorSystem.terminate(); // 这个方法终止 actor
    }
}
