package com.liparistudios.webSocketSpringFluxTraining.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

@Service
public class TriggerService {


    private List<WebSocketSession> session;


    public void addSession( WebSocketSession newSession ) {

        if( session == null ) session = new LinkedList<WebSocketSession>();

        this.session.add(newSession);
    }


    /**
     * Send message to clients are listening in speficic channel
     */
    public void broadcastEmitNotific() {


        Flux<String> nameStream = Flux.fromIterable( new LinkedList<>(){{
            add("uno");
            add("due");
            add("tre");
            add("quattro");
            add("cinque");
            add("sei");
            add("sette");
            add("otto");
        }});

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<String> clockStream = Flux.zip(nameStream, interval, (key, value) -> key);

//        clockStream
//            .doOnNext( streamElement -> {
//                System.out.println("clockStream doOnNext");
//                System.out.println( streamElement );
//            })
//            .subscribe()
//        ;


        Flux<Object> flux = Flux
            .create( emitter -> {
                int i = 0;
                while ( i < 10 ) {
                    int finalI = i;
                    emitter.next(new HashMap<String, Object>(){{
                        put(String.valueOf(finalI), (new Date()).getTime() );
                    }} );
                    i++;
                }
            })
            .publish()
            .autoConnect()
        ;

        flux
            .flatMap( streamElement -> Mono.just( streamElement ) )   // asincrono per questo invexce che ritornare un elemento torna un wrap di tipo Mono all'elemento
            .doOnNext( streamElement -> {
                System.out.println("flux doOnNext");
                System.out.println( streamElement );
            })
//            .subscribe()
        ;




            /*
        session
            .stream()
            .map( ses -> {
                clockStream.next();
                return ses;
            })
            .map( ses -> {
                System.out.println("current session: "+ ses.getId());
                flux.next();
                return ses;
            })
            .map( ses -> {
                try {
                    System.out.println("send to ---> "+ ses.getId() );
                    ses.sendMessage(new TextMessage("TRIGGER "+ ses.getId()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ses;
            })
        ;
            */


        session.forEach( ses -> {
            System.out.println("emit per session ---> "+ ses.getId() );


            clockStream
                .doOnNext( streamElement -> {
                    System.out.println("clockStream doOnNext");
                    System.out.println( streamElement );
                    try {
                        ses.sendMessage(new TextMessage("TRIGGER "+ ses.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .subscribe()
            ;

//                    ses.sendMessage(new TextMessage("TRIGGER "+ ses.getId()));

        });




    }


}
