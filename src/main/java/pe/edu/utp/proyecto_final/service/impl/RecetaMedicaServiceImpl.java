package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.RecetaMedicaDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.Medicamento;
import pe.edu.utp.proyecto_final.models.RecetaMedica;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.MedicamentoRepository;
import pe.edu.utp.proyecto_final.repository.RecetaMedicaRepository;
import pe.edu.utp.proyecto_final.service.RecetaMedicaService;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaMedicaServiceImpl implements RecetaMedicaService {

    @Autowired
    private RecetaMedicaRepository recetaMedicaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<RecetaMedica> getAllRecetasMedicas() {
        return recetaMedicaRepository.findAll();
    }

    @Override
    public Optional<RecetaMedica> getRecetaMedicaById(Long id) {
        return recetaMedicaRepository.findById(id);
    }

    @Override
    public RecetaMedica crearRecetaMedica(RecetaMedicaDTO recetaMedicaDTO) {
        // Reutilizamos la lógica de asignación creando una instancia nueva
        RecetaMedica recetaMedica = new RecetaMedica();
        return guardarDatosReceta(recetaMedica, recetaMedicaDTO);
    }

    @Override
    public RecetaMedica updateRecetaMedica(Long id, RecetaMedicaDTO recetaMedicaDTO) {
        // 1. Buscamos la receta existente (Aquí estaba tu línea cortada)
        RecetaMedica recetaMedica = recetaMedicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta médica no encontrada"));

        // 2. Actualizamos los datos y guardamos
        return guardarDatosReceta(recetaMedica, recetaMedicaDTO);
    }

    @Override
    public void deleteRecetaMedica(Long id) {
        recetaMedicaRepository.deleteById(id);
    }

    // Método auxiliar para evitar repetir código en Crear y Actualizar
    private RecetaMedica guardarDatosReceta(RecetaMedica recetaMedica, RecetaMedicaDTO recetaMedicaDTO) {

        // Buscar Cita
        Cita cita = citaRepository.findById(recetaMedicaDTO.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // Buscar Medicamento
        Medicamento medicamento = medicamentoRepository.findById(recetaMedicaDTO.getMedicamentoId())
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        // Asignar los valores (Setters que estaban en tu código desordenado)
        recetaMedica.setCita(cita);
        recetaMedica.setMedicamento(medicamento);
        recetaMedica.setDosis(recetaMedicaDTO.getDosis());
        recetaMedica.setFrecuencia(recetaMedicaDTO.getFrecuencia());
        recetaMedica.setDuracion(recetaMedicaDTO.getDuracion());

        return recetaMedicaRepository.save(recetaMedica);
    }
}