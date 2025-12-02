package pe.edu.utp.proyecto_final.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Medicamento que representa los medicamentos disponibles en la veterinaria.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "medicamentos")
@Data
@NoArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String principioActivo;
    private String laboratorio;
    private Double precioUnitario;
    private Integer stock;

    @Column(columnDefinition = "TEXT")
    private String indicaciones;
}
