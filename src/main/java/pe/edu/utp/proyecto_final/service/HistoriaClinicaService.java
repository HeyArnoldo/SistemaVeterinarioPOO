package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.HistoriaClinicaDTO;
import pe.edu.utp.proyecto_final.models.HistoriaClinica;

import java.util.List;
import java.util.Optional;

public interface HistoriaClinicaService {
    HistoriaClinica crearHistoriaClinica(HistoriaClinicaDTO historiaClinicaDTO);

    List<HistoriaClinica> getAllHistoriasClinicas();

    Optional<HistoriaClinica> getHistoriaClinicaById(Long id);

    HistoriaClinica updateHistoriaClinica(Long id, HistoriaClinicaDTO historiaClinicaDTO);

    void deleteHistoriaClinica(Long id);
}

