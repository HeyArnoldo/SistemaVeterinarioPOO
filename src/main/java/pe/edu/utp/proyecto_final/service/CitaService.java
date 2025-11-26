package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.CitaRequestDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    Cita agendarCita(CitaRequestDTO requestDTO);
    boolean isHorarioOcupado(LocalDate fecha, LocalTime hora);
    List<Cita> getCitasPorFecha(LocalDate fecha);
    List<Cita> getAllCitas();
    void deleteCita(Long id);
    Optional<Cita> getCitaById(Long id);
    Cita updateCita(Long id, CitaRequestDTO citaDTO);
}