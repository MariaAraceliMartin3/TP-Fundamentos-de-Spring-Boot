package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("BIENVENIDO AL SISTEMA DE GESTIÓN DE TAREAS");
        System.out.println("   -   Modo: DESARROLLO");
        System.out.println("   -   Logging detallado activado");
        System.out.println("   -   Estadísticas habilitadas");
        System.out.println("   -   Límite de tareas reducido para pruebas");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("GRACIAS POR USAR EL SISTEMA");
        System.out.println("      Modo desarrollo finalizado exitosamente");
        System.out.println("      ¡Feliz coding!");
    }
}
