package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
    private String nombres;
    private String apellidos;
    private String telefono;
    private String dni;
    private String email;
    private String direccion;
}
