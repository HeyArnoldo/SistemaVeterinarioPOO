package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.MedicamentoDTO;
import pe.edu.utp.proyecto_final.models.Medicamento;
import pe.edu.utp.proyecto_final.repository.MedicamentoRepository;
import pe.edu.utp.proyecto_final.service.MedicamentoService;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public Medicamento crearMedicamento(MedicamentoDTO medicamentoDTO) {
        // En tu código original usabas Builder aquí
        Medicamento medicamento = Medicamento.builder()
                .nombre(medicamentoDTO.getNombre())
                .principioActivo(medicamentoDTO.getPrincipioActivo())
                .laboratorio(medicamentoDTO.getLaboratorio())
                .precioUnitario(medicamentoDTO.getPrecioUnitario())
                .stock(medicamentoDTO.getStock())
                .indicaciones(medicamentoDTO.getIndicaciones())
                .build();

        return medicamentoRepository.save(medicamento);
    }

    @Override
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Optional<Medicamento> getMedicamentoById(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Override
    public Medicamento updateMedicamento(Long id, MedicamentoDTO medicamentoDTO) {
        // 1. Buscar
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        // 2. Actualizar (Setters)
        medicamento.setNombre(medicamentoDTO.getNombre());
        medicamento.setPrincipioActivo(medicamentoDTO.getPrincipioActivo());
        medicamento.setLaboratorio(medicamentoDTO.getLaboratorio());
        medicamento.setPrecioUnitario(medicamentoDTO.getPrecioUnitario());
        medicamento.setStock(medicamentoDTO.getStock());
        medicamento.setIndicaciones(medicamentoDTO.getIndicaciones());

        // 3. Guardar
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public void deleteMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }
}