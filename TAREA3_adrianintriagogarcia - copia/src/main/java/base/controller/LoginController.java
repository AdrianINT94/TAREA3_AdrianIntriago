package base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import base.config.StageManager;
import base.model.Credencial;
import base.service.CredencialService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Component
public class LoginController {
	
	
	@Autowired
	private CredencialService credencialService;
	
	@Autowired
	private StageManager stageManager;
	
	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML 
	private void login() {
		
			String user = txtUsuario.getText();
			String pass = txtPassword.getText();
			
			Credencial c = credencialService.getByUsername(user);
			
			
			if(c !=null && c.getPassword().equals(pass)) {
				System.out.println("Login correcto :" + c.getRol());
				
				
				stageManager.switchScene("/persona.fxml", "Persona");
				
			}else {
				System.out.println("Credenciales incorrectas");
			}
	}
}