package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.MedicamentoDTO;
import pe.edu.utp.proyecto_final.models.Medicamento;
import pe.edu.utp.proyecto_final.repository.MedicamentoRepository;
import pe.edu.utp.proyecto_final.service.impl.MedicamentoServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicamentoServiceTest {

    @Mock
    private MedicamentoRepository medicamentoRepository;

    @InjectMocks
    private MedicamentoServiceImpl medicamentoService;

    private MedicamentoDTO buildDTO() {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setNombre("Amoxicilina");
        dto.setPrincipioActivo("Amoxicilina");
        dto.setLaboratorio("LabVet");
        dto.setPrecioUnitario(10.0);
        dto.setStock(50);
        dto.setIndicaciones("Cada 12 horas por 5 dias");
        return dto;
    }

    @Test
    @DisplayName("crearMedicamento guarda correctamente los datos del DTO")
    void crearMedicamentoDebeGuardarDatosDelDto() {
        MedicamentoDTO dto = buildDTO();
        when(medicamentoRepository.save(any(Medicamento.class))).thenAnswer(inv -> inv.getArgument(0));

        Medicamento resultado = medicamentoService.crearMedicamento(dto);

        assertEquals(dto.getNombre(), resultado.getNombre());
        assertEquals(dto.getPrincipioActivo(), resultado.getPrincipioActivo());
        assertEquals(dto.getLaboratorio(), resultado.getLaboratorio());
        assertEquals(dto.getPrecioUnitario(), resultado.getPrecioUnitario());
        assertEquals(dto.getStock(), resultado.getStock());
        assertEquals(dto.getIndicaciones(), resultado.getIndicaciones());
    }

    @Test
    @DisplayName("updateMedicamento lanza error si el id no existe")
    void updateMedicamentoDebeFallarSiNoExiste() {
        MedicamentoDTO dto = buildDTO();
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> medicamentoService.updateMedicamento(1L, dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage())
                .contains("Medicamento no encontrado");
        verify(medicamentoRepository, never()).save(any());
    }
}
