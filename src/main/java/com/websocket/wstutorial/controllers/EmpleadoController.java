package com.websocket.wstutorial.controllers;

import com.websocket.wstutorial.crud.IEmpleadoCR;
import com.websocket.wstutorial.messaging.WSService;
import com.websocket.wstutorial.models.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpleadoController {

    @Autowired
    private WSService service;

    @Autowired
    IEmpleadoCR crud;

    @PostMapping("api/empleado")
    public Empleado createEmpleado(@RequestBody final Empleado empleado){
        Empleado newEmpleado = crud.save(empleado);
        service.notifySubscribersEmpleado(empleado);
        return newEmpleado;
    }
    @GetMapping("api/empleado")
    public List<Empleado> getEmpleados(){
        return crud.findAll();
    }
}
