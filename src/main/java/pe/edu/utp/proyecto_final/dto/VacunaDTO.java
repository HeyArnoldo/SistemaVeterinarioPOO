package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VacunaDTO {
    private String nombre;
    private String laboratorio;
    private String lote;
    private Integer diasProximaDosis;
}

