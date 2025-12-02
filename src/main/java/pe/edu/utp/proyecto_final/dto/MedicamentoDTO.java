package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicamentoDTO {
    private String nombre;
    private String principioActivo;
    private String laboratorio;
    private Double precioUnitario;
    private Integer stock;
    private String indicaciones;
}

