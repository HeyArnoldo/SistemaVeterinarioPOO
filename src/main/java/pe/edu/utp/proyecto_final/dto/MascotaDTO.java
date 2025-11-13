package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MascotaDTO {
    private Long clienteId;
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Boolean buenComportamiento;
}