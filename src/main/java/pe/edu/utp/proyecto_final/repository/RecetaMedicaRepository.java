package pe.edu.utp.proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto_final.models.RecetaMedica;

public interface RecetaMedicaRepository extends JpaRepository<RecetaMedica, Long> {
}
