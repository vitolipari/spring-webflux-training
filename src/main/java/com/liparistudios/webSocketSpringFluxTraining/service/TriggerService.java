package com.liparistudios.webSocketSpringFluxTraining.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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


        session.forEach( ses -> {
            try {

                if( ses != null ) {
                    System.out.println("emit ---> "+ ses.getId() );
                        ses.sendMessage(new TextMessage("TRIGGER "+ ses.getId()));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }


}
