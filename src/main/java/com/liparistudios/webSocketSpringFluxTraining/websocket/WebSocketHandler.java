package com.liparistudios.webSocketSpringFluxTraining.websocket;

import com.liparistudios.webSocketSpringFluxTraining.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalTime;

@Component
public class WebSocketHandler extends TextWebSocketHandler {


    @Autowired
    TriggerService triggerService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


        System.out.println("handleTextMessage");

        var clientMessage = message.getPayload();
        System.out.println("message arrived");
        System.out.println(clientMessage);
        System.out.println(message);
        System.out.println(message.isLast());

        var currentTime = LocalTime.now();
        session.sendMessage(new TextMessage(currentTime.toString()));


        triggerService.addSession( session );



    }




}