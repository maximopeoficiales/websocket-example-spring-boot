package com.websocket.wstutorial.controllers;

import com.websocket.wstutorial.models.Message;
import com.websocket.wstutorial.models.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
//esto es un controlador de prueba con una integracion sismple
//    se le indica el mapping y tambien a donde enviara la respuesta
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }
}
