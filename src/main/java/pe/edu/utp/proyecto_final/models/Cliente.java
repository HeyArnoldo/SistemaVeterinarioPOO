package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Data
@NoArgsConstructor
@Entity
@Table(name = "clientes")

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
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore //Es para evitar blucles infinitos xd
    private List<Mascota> mascotas;

    //Un cliente puede tener muchas citas
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cita> citas;


}
