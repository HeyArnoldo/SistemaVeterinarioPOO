package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.VacunaDTO;
import pe.edu.utp.proyecto_final.models.Vacuna;
import pe.edu.utp.proyecto_final.repository.VacunaRepository;
import pe.edu.utp.proyecto_final.service.VacunaService;

import java.util.List;
import java.util.Optional;

@Service
public class VacunaServiceImpl implements VacunaService {

    @Autowired
    private VacunaRepository vacunaRepository;

    @Override
    public Vacuna crearVacuna(VacunaDTO vacunaDTO) {
        Vacuna vacuna = Vacuna.builder()
                .nombre(vacunaDTO.getNombre())
                .laboratorio(vacunaDTO.getLaboratorio())
                .lote(vacunaDTO.getLote())
                .diasProximaDosis(vacunaDTO.getDiasProximaDosis())
                .build();

        return vacunaRepository.save(vacuna);
    }

    @Override
    public List<Vacuna> getAllVacunas() {
        return vacunaRepository.findAll();
    }

    @Override
    public Optional<Vacuna> getVacunaById(Long id) {
        return vacunaRepository.findById(id);
    }

    @Override
    public Vacuna updateVacuna(Long id, VacunaDTO vacunaDTO) {
        Vacuna vacuna = vacunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));

        vacuna.setNombre(vacunaDTO.getNombre());
        vacuna.setLaboratorio(vacunaDTO.getLaboratorio());
        vacuna.setLote(vacunaDTO.getLote());
        vacuna.setDiasProximaDosis(vacunaDTO.getDiasProximaDosis());

        return vacunaRepository.save(vacuna);
    }

    @Override
    public void deleteVacuna(Long id) {
        vacunaRepository.deleteById(id);
    }
}

