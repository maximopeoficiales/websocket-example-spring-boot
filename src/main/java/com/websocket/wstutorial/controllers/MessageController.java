package com.websocket.wstutorial.controllers;

import com.websocket.wstutorial.crud.IEmpleadoCR;
import com.websocket.wstutorial.models.Empleado;
import com.websocket.wstutorial.models.Message;
import com.websocket.wstutorial.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @Autowired
    IEmpleadoCR crud;

    //esto es un controlador de prueba con una integracion sismple
//    se le indica el mapping y tambien a donde enviara la respuesta
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
//        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/empleado")
    @SendTo("/topic/empleado")
    public Empleado getEmpleado(final Empleado empleado) throws  InterruptedException{
        Empleado newEmpleado= crud.save(empleado);
        return newEmpleado;
    }
}
