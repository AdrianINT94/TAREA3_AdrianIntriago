package base.model;

import jakarta.persistence.*; 

@Entity 
@Table(name = "personas") 
public class Persona {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento
    
    
    private Long id; 
    private String nombre;
    private String email;
    private String nacionalidad;

    
    public Persona() {}

    public Persona(Long id, String nombre, String email, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.nacionalidad = nacionalidad;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + ", nacionalidad=" + nacionalidad + "]";
    }
}
	
	
		
