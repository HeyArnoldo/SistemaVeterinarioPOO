package pe.edu.utp.proyecto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ComprobantePagoDTO {
    private LocalDateTime fechaEmision;
    private Double montoTotal;
    private String tipoComprobante; // Boleta, Factura
    private Long citaId;
}

