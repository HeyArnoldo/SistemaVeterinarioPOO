package pe.edu.utp.proyecto_final.service;

import java.util.Optional;
import java.util.List;

import pe.edu.utp.proyecto_final.models.Medicamento;
import pe.edu.utp.proyecto_final.dto.MedicamentoDTO;

public interface MedicamentoService {

    void deleteMedicamento(Long id);

    Medicamento updateMedicamento(Long id, MedicamentoDTO medicamentoDTO);

    Optional<Medicamento> getMedicamentoById(Long id);

    List<Medicamento> getAllMedicamentos();

    Medicamento crearMedicamento(MedicamentoDTO medicamentoDTO);
}