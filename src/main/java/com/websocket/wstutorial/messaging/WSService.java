package com.websocket.wstutorial.messaging;

import com.websocket.wstutorial.models.ResponseMessage;
import com.websocket.wstutorial.models.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {
//    esta haciendo una inyeccion de dependencia
    private final SimpMessagingTemplate messagingTemplate;
//    esto permite que se instancia el SimpMessagingTemplate
    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSend("/topic/messages", response);
    }


    public void notifySubscribersEmpleado(final Empleado empleado) {
//        va notificar a todos los subscriptores en /topic/empleado, le enviara el empleado
        messagingTemplate.convertAndSend("/topic/empleado", empleado);
    }
}
