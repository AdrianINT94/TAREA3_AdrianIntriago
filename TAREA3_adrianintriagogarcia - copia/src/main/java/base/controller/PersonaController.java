package base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import base.model.Persona;
import base.service.PersonaService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

@Component
public class PersonaController {
	
	
		
		private  PersonaService personaService;

		public PersonaController(PersonaService personaService) {
			this.personaService= personaService;
			
		}
		
		@FXML
		private ListView<String> listaPersonas;
		
		@FXML
		public void initialize() {
			List<Persona> personas = personaService.findAll();
			for(Persona p : personas) {
				listaPersonas.getItems().add(p.getId()+ " - "+ p.getNombre());
			}
		}
}
