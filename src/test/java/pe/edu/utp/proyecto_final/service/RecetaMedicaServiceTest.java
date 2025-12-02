package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.RecetaMedicaDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.Medicamento;
import pe.edu.utp.proyecto_final.models.RecetaMedica;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.MedicamentoRepository;
import pe.edu.utp.proyecto_final.repository.RecetaMedicaRepository;
import pe.edu.utp.proyecto_final.service.impl.RecetaMedicaServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaMedicaServiceTest {

    @Mock
    private RecetaMedicaRepository recetaMedicaRepository;

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private MedicamentoRepository medicamentoRepository;

    @InjectMocks
    private RecetaMedicaServiceImpl recetaMedicaService;

    private RecetaMedicaDTO buildDTO() {
        RecetaMedicaDTO dto = new RecetaMedicaDTO();
        dto.setDosis("1 tableta");
        dto.setFrecuencia("Cada 8 horas");
        dto.setDuracion("5 dias");
        dto.setCitaId(1L);
        dto.setMedicamentoId(2L);
        return dto;
    }

    @Test
    @DisplayName("crearRecetaMedica lanza error si la cita no existe")
    void crearRecetaMedicaDebeFallarSinCita() {
        RecetaMedicaDTO dto = buildDTO();
        when(citaRepository.findById(dto.getCitaId())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> recetaMedicaService.crearRecetaMedica(dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage())
                .contains("Cita no encontrada");
        verify(recetaMedicaRepository, never()).save(any());
    }

    @Test
    @DisplayName("crearRecetaMedica guarda receta asociada a cita y medicamento")
    void crearRecetaMedicaDebeGuardar() {
        RecetaMedicaDTO dto = buildDTO();
        Cita cita = new Cita();
        cita.setId(1L);
        Medicamento med = new Medicamento();
        med.setId(2L);

        when(citaRepository.findById(dto.getCitaId())).thenReturn(Optional.of(cita));
        when(medicamentoRepository.findById(dto.getMedicamentoId())).thenReturn(Optional.of(med));
        when(recetaMedicaRepository.save(any(RecetaMedica.class))).thenAnswer(inv -> inv.getArgument(0));

        RecetaMedica receta = recetaMedicaService.crearRecetaMedica(dto);

        assertEquals(dto.getDosis(), receta.getDosis());
        assertEquals(dto.getFrecuencia(), receta.getFrecuencia());
        assertEquals(dto.getDuracion(), receta.getDuracion());
        assertEquals(cita, receta.getCita());
        assertEquals(med, receta.getMedicamento());
    }
}
