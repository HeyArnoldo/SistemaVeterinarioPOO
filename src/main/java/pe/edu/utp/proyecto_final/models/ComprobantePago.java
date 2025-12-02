package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Clase ComprobantePago representa un comprobante de pago emitido por el sistema.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "comprobantes_pago")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ComprobantePago implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaEmision;
    private String serie;
    private String numero;
    private Double subtotal;
    private Double impuestos;
    private Double total;

    private String metodoPago; // Efectivo, Tarjeta, Yape

    // Generalmente, un comprobante se emite por una Cita finalizada
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @OneToOne
    @JoinColumn(name = "cita_id", unique = true)
    private Cita cita;
}
