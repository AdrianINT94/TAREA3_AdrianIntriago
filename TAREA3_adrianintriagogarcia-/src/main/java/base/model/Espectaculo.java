package base.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "espectaculos")
public class Espectaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 25)
    private String nombre;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "coordinador_id", nullable = false)
    private Coordinador coordinador;

    // 🔹 Constructor vacío obligatorio para JPA
    public Espectaculo() {
    }

    // 🔹 Constructor útil para crear objetos
    public Espectaculo(String nombre, LocalDate fechaInicio, LocalDate fechaFin, Coordinador coordinador) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.coordinador = coordinador;
    }

    // 🔹 Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    // 🔹 Setters (NECESARIOS para formularios y JPA)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    // 🔹 Útil para ListView / ComboBox
    @Override
    public String toString() {
        return nombre;
    }
}