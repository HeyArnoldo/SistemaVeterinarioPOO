package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoriaClinicaDTO {
    private String observaciones;
    private Double peso;
    private Double temperatura;
    private String frecuenciaCardiaca;
    private Long citaId;
}

