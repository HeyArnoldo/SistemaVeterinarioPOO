package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProveedorDTO {
    private String ruc;
    private String razonSocial;
    private String telefono;
    private String email;
    private String direccion;
}

