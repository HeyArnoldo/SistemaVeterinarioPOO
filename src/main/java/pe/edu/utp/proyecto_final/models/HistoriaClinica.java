package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Clase HistoriaClinica que representa el historial clínico de una mascota.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "historias_clinicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaRegistro;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private Double peso;
    private Double temperatura;
    private String frecuenciaCardiaca;

    // Relación directa con la Cita que generó este historial
    @OneToOne
    @JoinColumn(name = "cita_id", unique = true)
    private Cita cita;
}
