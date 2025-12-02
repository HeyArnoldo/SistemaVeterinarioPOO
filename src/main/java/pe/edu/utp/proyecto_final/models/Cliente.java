package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
-----------------------------------
AGENDAR UNA CITA DE BAÑO DE PERROS
-----------------------------------
1. Recibir la solicitud del cliente -> Llamada o Presencial
2. Consultar disponibilidad -> Verificación de horario
3. Solicitar datos del cliente y el perro
4. Confirmar el servicio requerido
5. Registrar la cita en el sistema
6. Enviar confirmación al cliente
7. Recordatorio automático
8. Registro de llegada
9. Finalización del servicio
-----------------------------------
*/

/**
 * Clase Cliente que representa a un cliente del servicio de peluquería canina.
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;

    @Column(unique = true, nullable = false)
    private String dni;

    private String telefono;
    private String email;
    private String direccion;

    //RELACIONES
    //Un cliente puede tener muchas mascotas
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore //Es para evitar blucles infinitos xd
    private List<Mascota> mascotas;

    //Un cliente puede tener muchas citas
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cita> citas;
}
