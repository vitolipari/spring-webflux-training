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


        Flux<String> nameStream =
            Flux
                .fromIterable( new LinkedList<String>(){{
                    add("uno");
                    add("due");
                    add("tre");
                    add("quattro");
                    add("cinque");
                    add("sei");
                    add("sette");
                    add("otto");
                }})
                .flatMap( streamElement -> {
                    System.out.println("clockStream doOnNext");
                    System.out.println( streamElement );
                    return Mono.just( streamElement );
                })
        ;

        Flux<Long> interval = Flux.interval(Duration.ofMillis(500));
        Flux<String> clockStream = Flux.zip(nameStream, interval, (key, value) -> key);



        session.forEach( ses -> {
            System.out.println("emit per session ---> "+ ses.getId() );

            clockStream
                .doOnNext( streamElement -> {
                    try {
                        ses.sendMessage(new TextMessage("TRIGGER "+ ses.getId() +" "+ streamElement));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .subscribe()
            ;

        });




    }


}
