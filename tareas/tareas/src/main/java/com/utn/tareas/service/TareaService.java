package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class TareaService {
    private final TareaRepository tareaRepository;

    @Value("${app.nombre}")
    private String nombreApp;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        if (tareaRepository.contarTareas() >= maxTareas) {
            System.out.println("No es posible agregar más tareas. El límite de tareas ha sido alcanzado" + maxTareas);
            return null;
        }

        Tarea tarea = new Tarea();
        tarea.setDescripcion(descripcion);
        tarea.setPrioridad(prioridad);
        tarea.setCompletada(false);

        return tareaRepository.guardarTarea(tarea);
    }

    public List<Tarea> listarTodasLasTareas() {
        return tareaRepository.obtenerTodasLasTareas();
    }

    public List<Tarea> listarTareasPendientes() {
        return tareaRepository.obtenerTodasLasTareas()
                .stream()
                .filter(tarea -> !tarea.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarTareasCompletadas() {
        return tareaRepository.obtenerTodasLasTareas()
                .stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public boolean marcarTareaComoCompletada(Long id) {
        return tareaRepository.buscarPorId(id)
                .map(tarea -> {
                    tarea.setCompletada(true);
                    return true;
                })
                .orElse(false);
    }

    public String obtenerEstadisticas() {
        long total = tareaRepository.obtenerTodasLasTareas().size();
        long completadas = listarTareasCompletadas().size();
        long pendientes = listarTareasPendientes().size();

        return String.format(
                "ESTADÍSTICAS" +
                        "-  Total: " + total +
                        "-  Completadas: " + completadas +
                        "-  Pendientes: " + pendientes +
                        "____________________",
                total, completadas, pendientes
        );
    }

    public void mostrarConfiguracion() {
        System.out.println("CONFIGURACIÓN");
        System.out.println("Nombre: " + nombreApp);
        System.out.println("Máximo de tareas: " + maxTareas);
        System.out.println("Mostrar estadísticas: " + mostrarEstadisticas);
        System.out.println("____________________");
    }

    public boolean mostrarEstadisticas() {
        return mostrarEstadisticas;
    }
}
