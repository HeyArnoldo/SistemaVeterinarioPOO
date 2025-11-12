package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaRequestDTO {
    private Long clienteId;
    private Long mascotaId;
    private LocalDate fecha;
    private LocalTime hora;
    private TipoServicio servicio;
}
