package com.utn.tareas.repository;

import com.utn.tareas.model.Tarea;
import com.utn.tareas.model.Prioridad;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {
    private final List<Tarea> tareas;
    private final AtomicLong idGenerator;

    public TareaRepository() {
        this.tareas = new ArrayList<>();
        this.idGenerator = new AtomicLong(1);

        tareas.add(new Tarea(idGenerator.getAndIncrement(),
                "Estudiar Spring Boot fundamentos",
                false,
                Prioridad.ALTA));

        tareas.add(new Tarea(idGenerator.getAndIncrement(),
                "Completar ejercicios de inyección de dependencias",
                true,
                Prioridad.MEDIA));

        tareas.add(new Tarea(idGenerator.getAndIncrement(),
                "Revisar documentación de Spring Framework",
                false,
                Prioridad.BAJA));

        tareas.add(new Tarea(idGenerator.getAndIncrement(),
                "Configurar profiles para diferentes entornos",
                false,
                Prioridad.ALTA));

        tareas.add(new Tarea(idGenerator.getAndIncrement(),
                "Implementar pruebas unitarias del servicio",
                true,
                Prioridad.MEDIA));
    }

    public Tarea guardarTarea(Tarea tarea) {
        tarea.setId(idGenerator.getAndIncrement());
        tareas.add(tarea);
        return tarea;
    }


    public List<Tarea> obtenerTodasLasTareas() {
        return new ArrayList<>(tareas);
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream()
                .filter(tarea -> tarea.getId().equals(id))
                .findFirst();
    }

    public boolean eliminarPorId(Long id) {
        return tareas.removeIf(tarea -> tarea.getId().equals(id));
    }

    public int contarTareas() {
        return tareas.size();
    }
}
