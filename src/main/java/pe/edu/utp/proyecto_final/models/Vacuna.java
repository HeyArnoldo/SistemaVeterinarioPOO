package pe.edu.utp.proyecto_final.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Vacuna que representa las vacunas administradas a las mascotas.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "vacunas")
@Data
@NoArgsConstructor
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String laboratorio;
    private String lote;
    private Integer diasProximaDosis; // Para calcular revacunación
}
