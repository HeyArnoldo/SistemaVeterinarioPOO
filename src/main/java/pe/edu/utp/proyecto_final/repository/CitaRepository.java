package pe.edu.utp.proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto_final.models.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    //Consulta personalizada para buscar citas por fecha:
    List<Cita> findByFecha(LocalDate fecha);

    //Consulta para verificar disponibilidad:
    List<Cita> findByFechaAndHora(LocalDate fecha, LocalTime hora);
}
