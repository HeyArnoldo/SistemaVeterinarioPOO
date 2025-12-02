package pe.edu.utp.proyecto_final.service;

import java.util.Optional;
import java.util.List;

import pe.edu.utp.proyecto_final.models.RecetaMedica;
import pe.edu.utp.proyecto_final.dto.RecetaMedicaDTO;

public interface RecetaMedicaService {
    void deleteRecetaMedica(Long id);

    RecetaMedica updateRecetaMedica(Long id, RecetaMedicaDTO recetaMedicaDTO);

    Optional<RecetaMedica> getRecetaMedicaById(Long id);

    List<RecetaMedica> getAllRecetasMedicas();

    RecetaMedica crearRecetaMedica(RecetaMedicaDTO recetaMedicaDTO);
}