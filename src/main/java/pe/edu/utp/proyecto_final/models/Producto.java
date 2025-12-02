package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase Producto que representa los productos disponibles en la tienda de mascotas.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoBarras;
    private String nombre;
    private String categoria; // Ej: Juguetes, Alimento
    private String marca;
    private Double precio;
    private Integer stock;
}
