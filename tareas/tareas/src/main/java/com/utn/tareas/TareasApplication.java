package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication

public class TareasApplication implements CommandLineRunner {

	private final TareaService tareaService;
	private final MensajeService mensajeService;

	public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mensajeService.mostrarBienvenida();

		tareaService.mostrarConfiguracion();

		System.out.println("TAREAS INICIALES");
		mostrarListaDeTareas(tareaService.listarTodasLasTareas());

		System.out.println("AGREGAR NUEVA TAREA");
		Tarea nuevaTarea = tareaService.agregarTarea(
				"Crear aplicación completa con Spring Boot",
				Prioridad.ALTA
		);
		System.out.println("   -   Tarea agregada: " + nuevaTarea.getDescripcion());

		System.out.println("TAREAS PENDIENTES");
		mostrarListaDeTareas(tareaService.listarTareasPendientes());

		System.out.println("MARCAR TAREA COMO COMPLETADA");
		Long idACompletar = 1L;
		boolean marcada = tareaService.marcarTareaComoCompletada(idACompletar);
		if (marcada) {
			System.out.println("   -   Tarea #" + idACompletar + " marcada como completada");
		} else {
			System.out.println("   -   No se encontró la tarea #" + idACompletar);
		}

		if (tareaService.mostrarEstadisticas()) {
			System.out.println(tareaService.obtenerEstadisticas());
		}

		System.out.println("TAREAS COMPLETADAS");
		mostrarListaDeTareas(tareaService.listarTareasCompletadas());

		mensajeService.mostrarDespedida();
	}

	private void mostrarListaDeTareas(List<Tarea> tareas) {
		if (tareas.isEmpty()) {
			System.out.println("No se han encontrado tareas");
			return;
		}

		tareas.forEach(tarea -> {
			String estado = tarea.isCompletada() ? "✓" : "○";
			String prioridad = obtenerIconoPrioridad(tarea.getPrioridad());
			System.out.printf("  %s [ID: %d] %s %s - %s%n",
					estado,
					tarea.getId(),
					prioridad,
					tarea.getDescripcion(),
					tarea.getPrioridad());
		});
	}

	private String obtenerIconoPrioridad(Prioridad prioridad) {
		return switch (prioridad) {
			case ALTA -> "🔴";
			case MEDIA -> "🟡";
			case BAJA -> "🟢";
		};
	}
}
