package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecetaMedicaDTO {
    private String dosis;
    private String frecuencia;
    private String duracion;
    private Long citaId;
    private Long medicamentoId;
}

