package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * Clase Medicamento que representa los medicamentos disponibles en la veterinaria.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "medicamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
