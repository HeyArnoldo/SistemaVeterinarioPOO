package pe.edu.utp.proyecto_final.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase Veterinario que representa a los veterinarios en el sistema de gestión veterinaria.
 *
 * @author Proyecto Final UTP Grupo 5
 * @version 1.0
 */

@Entity
@Table(name = "veterinarios")
@Data
@NoArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String cmvpString; // Código de Colegio Médico Veterinario

    private String especialidad;
    private String telefono;
    private String email;

    @OneToMany(mappedBy = "veterinario")
    @JsonIgnore
    private List<Cita> citasAtendidas;
}
