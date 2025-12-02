package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * Clase Vacuna que representa las vacunas administradas a las mascotas.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "vacunas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String laboratorio;
    private String lote;
    private Integer diasProximaDosis; // Para calcular revacunación
}
