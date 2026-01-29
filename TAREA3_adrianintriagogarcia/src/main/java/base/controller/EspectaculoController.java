package base.controller;

import org.springframework.stereotype.Controller;

import base.service.EspectaculoService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

@Controller
public class EspectaculoController {
	
	private final EspectaculoService espectaculoService;
	
	public EspectaculoController(EspectaculoService espectaculoService) {
		this.espectaculoService = espectaculoService;
	}
	
	@FXML
	public ListView<String> listaEspectaculos;
	
	@FXML
	public void initialize() {
		espectaculoService.findAll().forEach(e ->
		listaEspectaculos.getItems().add(
				e.getNombre() + " (" + e.getFechaInicio() + " - " + e.getFechaFin() + ")"
				)
		);
	}
}
