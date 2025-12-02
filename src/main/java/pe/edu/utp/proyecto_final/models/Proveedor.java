package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * Clase Proveedor que representa a los proveedores de productos y servicios para la veterinaria.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruc;
    private String razonSocial;
    private String telefono;
    private String email;
    private String direccion;
}
