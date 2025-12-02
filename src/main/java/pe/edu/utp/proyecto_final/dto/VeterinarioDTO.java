package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VeterinarioDTO {
    private String nombres;
    private String apellidos;
    private String cmvp;
    private String especialidad;
    private String telefono;
    private String email;
}

