package model;

public class EnvioCasa {

		private Long id;
		private Long idParada;
		private double peso;
		private int[] volumen;
		private boolean urgente;
		
		public EnvioCasa() {}

		public EnvioCasa(Long id, Long idParada, double peso, int[] volumen, boolean urgente) {
			super();
			this.id = id;
			this.idParada = idParada;
			this.peso = peso;
			this.volumen = volumen;
			this.urgente = urgente;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getIdParada() {
			return idParada;
		}

		public void setIdParada(Long idParada) {
			this.idParada = idParada;
		}

		public double getPeso() {
			return peso;
		}

		public void setPeso(double peso) {
			this.peso = peso;
		}

		public int[] getVolumen() {
			return volumen;
		}

		public void setVolumen(int[] volumen) {
			this.volumen = volumen;
		}

		public boolean isUrgente() {
			return urgente;
		}

		public void setUrgente(boolean urgente) {
			this.urgente = urgente;
		}
		
		
		
}
