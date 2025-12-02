package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.HistoriaClinicaDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.HistoriaClinica;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.HistoriaClinicaRepository;
import pe.edu.utp.proyecto_final.service.impl.HistoriaClinicaServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoriaClinicaServiceTest {

    @Mock
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private HistoriaClinicaServiceImpl historiaClinicaService;

    private HistoriaClinicaDTO buildDTO() {
        HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
        dto.setObservaciones("Paciente estable");
        dto.setPeso(10.5);
        dto.setTemperatura(38.0);
        dto.setFrecuenciaCardiaca("90");
        dto.setCitaId(1L);
        return dto;
    }

    @Test
    @DisplayName("crearHistoriaClinica lanza error si la cita no existe")
    void crearHistoriaDebeFallarSinCita() {
        HistoriaClinicaDTO dto = buildDTO();
        when(citaRepository.findById(dto.getCitaId())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> historiaClinicaService.crearHistoriaClinica(dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage()).contains("Cita no encontrada");
        verify(historiaClinicaRepository, never()).save(any());
    }

    @Test
    @DisplayName("crearHistoriaClinica guarda correctamente los datos y la cita asociada")
    void crearHistoriaDebeGuardar() {
        HistoriaClinicaDTO dto = buildDTO();
        Cita cita = new Cita();
        cita.setId(1L);

        when(citaRepository.findById(dto.getCitaId())).thenReturn(Optional.of(cita));
        when(historiaClinicaRepository.save(any(HistoriaClinica.class))).thenAnswer(inv -> inv.getArgument(0));

        HistoriaClinica historia = historiaClinicaService.crearHistoriaClinica(dto);

        assertEquals(dto.getObservaciones(), historia.getObservaciones());
        assertEquals(dto.getPeso(), historia.getPeso());
        assertEquals(dto.getTemperatura(), historia.getTemperatura());
        assertEquals(dto.getFrecuenciaCardiaca(), historia.getFrecuenciaCardiaca());
        assertEquals(cita, historia.getCita());
    }
}
