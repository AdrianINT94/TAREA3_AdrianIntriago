package base.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
	@Table(name="credenciales")
	public class Credencial{
		
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column (unique =true,nullable =false)
		private String nombreusuario;
		
		@Column (nullable = false)
		private String password;
		
		@Enumerated (EnumType.STRING)
		private Rol rol;
		
		@ManyToOne
		@JoinColumn(name ="persona_id",nullable =false)
		
		private Persona persona;
		
		public Credencial() {}

		public Credencial (String nombreusuario,String password,Rol rol, Persona persona) {
			this.nombreusuario = nombreusuario;
			this.password =password;
			this.rol =rol;
			this.persona=persona;
		}
		
		public Long getId() {
			return id;
		}
		
		public String getUsername() {
			return nombreusuario;
		}
		
		public void setUsername(String nombreusuario) {
			this.nombreusuario = nombreusuario;
		}
		public String getPassword() {
			return password;
			
		}
		
		public Rol getRol() {
			return rol;
		}
		
		public Persona getPersona() {
			return persona;
		}
		}

		

