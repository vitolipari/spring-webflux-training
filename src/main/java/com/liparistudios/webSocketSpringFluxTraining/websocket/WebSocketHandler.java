package com.liparistudios.webSocketSpringFluxTraining.websocket;

import com.liparistudios.webSocketSpringFluxTraining.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
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

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        System.out.println("Connesstioe stabilita");

        System.out.println("session.getAttributes()");
        System.out.println(session.getAttributes());

        System.out.println("session.getExtensions()");
        System.out.println(session.getExtensions());

        System.out.println("session.getHandshakeHeaders()");
        System.out.println(session.getHandshakeHeaders());

        System.out.println("session.getId()");
        System.out.println(session.getId());

        System.out.println("session.getLocalAddress()");
        System.out.println(session.getLocalAddress());

        System.out.println("session.getPrincipal()");
        System.out.println(session.getPrincipal());

        System.out.println("session.getRemoteAddress()");
        System.out.println(session.getRemoteAddress());

        System.out.println("session.getUri()");
        System.out.println(session.getUri());


    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        System.out.println("connection closed");
    }








}