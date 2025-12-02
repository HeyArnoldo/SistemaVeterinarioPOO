package pe.edu.utp.proyecto_final.models;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase Cita que representa una cita para el servicio de peluquería canina.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
public class Cita implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;
    private Double precio;

    @Enumerated(EnumType.STRING)
    private TipoServicio servicio;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

}
