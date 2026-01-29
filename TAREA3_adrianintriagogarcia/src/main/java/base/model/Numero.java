package base.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="numeros")
public class Numero{

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Double duracion;
	private int orden;
	
	@ManyToMany
	@JoinTable(
			name="numero_artista",
	joinColumns =@JoinColumn(name="numero_id"),
	inverseJoinColumns=@JoinColumn(name="artista_id")
		)
	private List<Artista>artistas = new ArrayList<>();
	
	public Numero() {}

	public Numero(String nombre, Double duracion, int orden) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.orden = orden;
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

	public Double getDuracion() {
		return duracion;
	}

	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	

	
	
}


	
	

	
	
	
	

