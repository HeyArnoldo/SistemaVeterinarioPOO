package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Clase Mascota que representa a las mascotas de los clientes en el sistema de gestión veterinaria.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "mascotas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Boolean buenComportamiento;

    //Relaciones
    //Muchas mascotas perteneces a UN cliente;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //Una mascota puede tener muchas citas
    @OneToMany(mappedBy = "mascota", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cita> citas;

}
