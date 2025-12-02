package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CarnetVacunacionDTO {
    private LocalDate fechaAplicacion;
    private LocalDate fechaProxima;
    private Long mascotaId;
    private Long vacunaId;
    private Long veterinarioId;
}

