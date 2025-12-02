package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDTO {
    private String codigoBarras;
    private String nombre;
    private String categoria;
    private String marca;
    private Double precio;
    private Integer stock;
}

