package pe.edu.utp.proyecto_final.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase RecetaMedica que representa una receta médica asociada a una cita y un medicamento.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "recetas_medicas")
@Data
@NoArgsConstructor
public class RecetaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dosis;
    private String frecuencia; // Ej: Cada 8 horas
    private String duracion;   // Ej: Por 5 días

    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;
}
