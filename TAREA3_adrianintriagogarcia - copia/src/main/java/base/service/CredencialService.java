package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.model.Credencial;
import base.repository.CredencialRepository;

@Service
public class CredencialService {
	
	@Autowired
	private CredencialRepository credencialRepository;
	
	public Credencial getByUsername(String username) {
		return credencialRepository.findByNombreusuario(username);
	}
	
	public Credencial guardar (Credencial credencial) {
		return credencialRepository.save(credencial);
	}


}