package model;

public class ConjuntoContratado {
	
	private Long id;
	private Long idEstancia;
	private String modoPago;
	private double precioTotal;
	private String extra;
	
	
	public ConjuntoContratado() {}

	
	public ConjuntoContratado(Long id, Long idEstancia, String modoPago, double precioTotal, String extra) {
		super();
		this.id = id;
		this.idEstancia = idEstancia;
		this.modoPago = modoPago;
		this.precioTotal = precioTotal;
		this.extra = extra;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdEstancia() {
		return idEstancia;
	}


	public void setIdEstancia(Long idEstancia) {
		this.idEstancia = idEstancia;
	}


	public String getModoPago() {
		return modoPago;
	}


	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}


	public double getPrecioTotal() {
		return precioTotal;
	}


	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}


	public String getExtra() {
		return extra;
	}


	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	
	
}
