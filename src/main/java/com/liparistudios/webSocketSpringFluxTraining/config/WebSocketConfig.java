package com.liparistudios.webSocketSpringFluxTraining.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler myWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // canale 1
        registry
            .addHandler(myWebSocketHandler, "/socketHandler/{channel}")
            .setAllowedOrigins("*")
        ;

        // canale 2
//        registry
//            .addHandler(myWebSocketHandler, "/socketHandler/{channel}")
//            .setAllowedOrigins("*");
//        ;


    }




}
