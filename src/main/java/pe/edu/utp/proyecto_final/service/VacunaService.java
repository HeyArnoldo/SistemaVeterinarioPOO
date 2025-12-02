package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.VacunaDTO;
import pe.edu.utp.proyecto_final.models.Vacuna;

import java.util.List;
import java.util.Optional;

public interface VacunaService {
    Vacuna crearVacuna(VacunaDTO vacunaDTO);

    List<Vacuna> getAllVacunas();

    Optional<Vacuna> getVacunaById(Long id);

    Vacuna updateVacuna(Long id, VacunaDTO vacunaDTO);

    void deleteVacuna(Long id);
}

