package model;

import java.util.ArrayList;
import java.util.List;

public class Numero {
	private int id;
	private String nombre;
	private Double duracion; 
	private int orden;
	private List <Artista> artistas;
	
	public Numero() {
	    this.artistas = new ArrayList<>();
	}
	
	
	public Numero(int id, String nombre, Double duracion, int orden, List<Artista> artistas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.orden = orden;
		this.artistas = artistas;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	

	
	
	
	

