package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.ComprobantePagoDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.ComprobantePago;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.ComprobantePagoRepository;
import pe.edu.utp.proyecto_final.service.impl.ComprobantePagoServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComprobantePagoServiceTest {

    @Mock
    private ComprobantePagoRepository comprobantePagoRepository;

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private ComprobantePagoServiceImpl comprobantePagoService;

    private ComprobantePagoDTO buildDTO() {
        ComprobantePagoDTO dto = new ComprobantePagoDTO();
        dto.setFechaEmision(LocalDateTime.of(2024, 10, 1, 10, 0));
        dto.setMontoTotal(150.0);
        dto.setTipoComprobante("EFECTIVO");
        dto.setCitaId(1L);
        return dto;
    }

    @Test
    @DisplayName("crearComprobantePago lanza error si la cita no existe")
    void crearComprobanteDebeFallarSinCita() {
        ComprobantePagoDTO dto = buildDTO();
        when(citaRepository.findById(dto.getCitaId())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> comprobantePagoService.crearComprobantePago(dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage()).contains("Cita no encontrada");
        verify(comprobantePagoRepository, never()).save(any());
    }

    @Test
    @DisplayName("crearComprobantePago guarda correctamente los datos y asocia la cita")
    void crearComprobanteDebeGuardar() {
        ComprobantePagoDTO dto = buildDTO();
        Cita cita = new Cita();
        cita.setId(1L);

        when(citaRepository.findById(dto.getCitaId())).thenReturn(Optional.of(cita));
        when(comprobantePagoRepository.save(any(ComprobantePago.class))).thenAnswer(inv -> inv.getArgument(0));

        ComprobantePago comprobante = comprobantePagoService.crearComprobantePago(dto);

        assertEquals(dto.getFechaEmision(), comprobante.getFechaEmision());
        assertEquals(dto.getMontoTotal(), comprobante.getTotal());
        assertEquals(dto.getMontoTotal(), comprobante.getSubtotal());
        assertEquals(dto.getTipoComprobante(), comprobante.getMetodoPago());
        assertEquals(cita, comprobante.getCita());
    }
}
