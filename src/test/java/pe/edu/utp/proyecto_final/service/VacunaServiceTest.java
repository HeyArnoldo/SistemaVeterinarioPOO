package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.VacunaDTO;
import pe.edu.utp.proyecto_final.models.Vacuna;
import pe.edu.utp.proyecto_final.repository.VacunaRepository;
import pe.edu.utp.proyecto_final.service.impl.VacunaServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacunaServiceTest {

    @Mock
    private VacunaRepository vacunaRepository;

    @InjectMocks
    private VacunaServiceImpl vacunaService;

    private VacunaDTO buildDTO() {
        VacunaDTO dto = new VacunaDTO();
        dto.setNombre("Antirrábica");
        dto.setLaboratorio("VetLab");
        dto.setLote("L123");
        dto.setDiasProximaDosis(365);
        return dto;
    }

    @Test
    @DisplayName("crearVacuna guarda correctamente los datos del DTO")
    void crearVacunaDebeGuardarDatosDelDto() {
        VacunaDTO dto = buildDTO();
        when(vacunaRepository.save(any(Vacuna.class))).thenAnswer(inv -> inv.getArgument(0));

        Vacuna resultado = vacunaService.crearVacuna(dto);

        assertEquals(dto.getNombre(), resultado.getNombre());
        assertEquals(dto.getLaboratorio(), resultado.getLaboratorio());
        assertEquals(dto.getLote(), resultado.getLote());
        assertEquals(dto.getDiasProximaDosis(), resultado.getDiasProximaDosis());
    }

    @Test
    @DisplayName("updateVacuna lanza error si el id no existe")
    void updateVacunaDebeFallarSiNoExiste() {
        VacunaDTO dto = buildDTO();
        when(vacunaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> vacunaService.updateVacuna(1L, dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage()).contains("Vacuna no encontrada");
        verify(vacunaRepository, never()).save(any());
    }
}
