package com.websocket.wstutorial.crud;

import com.websocket.wstutorial.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoCR extends JpaRepository<Empleado, Integer> {
}
