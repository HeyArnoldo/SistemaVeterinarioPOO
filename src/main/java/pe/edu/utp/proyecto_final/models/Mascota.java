package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name= "mascotas")

public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Boolean buenComportamiento;

    //Relaciones
    //Muchas mascotas perteneces a UN cliente;
    @ManyToOne
    @JoinColumn( name="cliente_id", nullable = false)
    private Cliente cliente;

    //Una mascota puede tener muchas citas
    @OneToMany(mappedBy = "mascota")
    @JsonIgnore
    private List<Cita> citas;

}
