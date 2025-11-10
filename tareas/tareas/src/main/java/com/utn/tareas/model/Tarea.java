package com.utn.tareas.model;

public class Tarea {

    private Long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;

    public Tarea() {
    }

    public Tarea(Long id, String descripcion, boolean completada, Prioridad prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.completada = completada;
        this.prioridad = prioridad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
}

