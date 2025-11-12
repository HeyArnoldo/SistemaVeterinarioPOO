package pe.edu.utp.proyecto_final.models;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "citas")

public class Cita {

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

}
