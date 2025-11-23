package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.CitaRequestDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;
import pe.edu.utp.proyecto_final.repository.MascotaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private ServicioService servicioService;

    @InjectMocks
    private CitaService citaService;

    private CitaRequestDTO requestDTO;
    private Cliente cliente;
    private Mascota mascota;

    @BeforeEach
    void setUp() {
        requestDTO = new CitaRequestDTO();
        requestDTO.setClienteId(1L);
        requestDTO.setMascotaId(2L);
        requestDTO.setFecha(LocalDate.of(2024, 10, 20));
        requestDTO.setHora(LocalTime.of(10, 0));
        requestDTO.setServicio(TipoServicio.BASICO);

        cliente = new Cliente();
        cliente.setId(1L);

        mascota = new Mascota();
        mascota.setId(2L);
        mascota.setCliente(cliente);
    }

    @Test
    @DisplayName("agendarCita lanza error si el horario esta ocupado")
    void agendarCitaDebeFallarPorHorarioOcupado() {
        when(citaRepository.findByFechaAndHora(requestDTO.getFecha(), requestDTO.getHora()))
                .thenReturn(List.of(new Cita()));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> citaService.agendarCita(requestDTO));

        assertTrue(ex.getMessage().contains("Horario no disponible"));
        verify(citaRepository, never()).save(any());
    }

    @Test
    @DisplayName("agendarCita valida que la mascota pertenezca al cliente")
    void agendarCitaDebeValidarPropiedadMascota() {
        when(citaRepository.findByFechaAndHora(requestDTO.getFecha(), requestDTO.getHora())).thenReturn(List.of());
        Mascota mascotaDeOtro = new Mascota();
        mascotaDeOtro.setId(2L);
        mascotaDeOtro.setCliente(new Cliente());
        when(clienteRepository.findById(requestDTO.getClienteId())).thenReturn(Optional.of(cliente));
        when(mascotaRepository.findById(requestDTO.getMascotaId())).thenReturn(Optional.of(mascotaDeOtro));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> citaService.agendarCita(requestDTO));

        assertTrue(ex.getMessage().contains("mascota no pertenece"));
        verify(citaRepository, never()).save(any());
    }

    @Test
    @DisplayName("agendarCita registra la cita con precio segun servicio")
    void agendarCitaDebeRegistrarCita() {
        when(citaRepository.findByFechaAndHora(requestDTO.getFecha(), requestDTO.getHora())).thenReturn(List.of());
        when(clienteRepository.findById(requestDTO.getClienteId())).thenReturn(Optional.of(cliente));
        when(mascotaRepository.findById(requestDTO.getMascotaId())).thenReturn(Optional.of(mascota));
        when(servicioService.getPrecio(TipoServicio.BASICO)).thenReturn(40.0);
        when(citaRepository.save(any(Cita.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cita cita = citaService.agendarCita(requestDTO);

        assertEquals(requestDTO.getFecha(), cita.getFecha());
        assertEquals(requestDTO.getHora(), cita.getHora());
        assertEquals(requestDTO.getServicio(), cita.getServicio());
        assertEquals(cliente, cita.getCliente());
        assertEquals(mascota, cita.getMascota());
        assertEquals(40.0, cita.getPrecio());
    }

    @Test
    @DisplayName("isHorarioOcupado devuelve true cuando hay citas en el mismo horario")
    void isHorarioOcupadoDebeRetornarTrue() {
        when(citaRepository.findByFechaAndHora(requestDTO.getFecha(), requestDTO.getHora()))
                .thenReturn(List.of(new Cita()));

        assertTrue(citaService.isHorarioOcupado(requestDTO.getFecha(), requestDTO.getHora()));
    }

    @Test
    @DisplayName("updateCita valida disponibilidad y recalcula precio")
    void updateCitaDebeValidarHorarioYPrecio() {
        Cita existente = new Cita();
        existente.setId(5L);
        existente.setFecha(requestDTO.getFecha());
        existente.setHora(requestDTO.getHora());

        CitaRequestDTO cambios = new CitaRequestDTO();
        cambios.setFecha(requestDTO.getFecha().plusDays(1));
        cambios.setHora(requestDTO.getHora());
        cambios.setServicio(TipoServicio.PREMIUM);

        when(citaRepository.findById(5L)).thenReturn(Optional.of(existente));
        when(citaRepository.findByFechaAndHora(cambios.getFecha(), cambios.getHora())).thenReturn(List.of());
        when(servicioService.getPrecio(TipoServicio.PREMIUM)).thenReturn(130.0);
        when(citaRepository.save(any(Cita.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cita actualizada = citaService.updateCita(5L, cambios);

        assertEquals(cambios.getFecha(), actualizada.getFecha());
        assertEquals(cambios.getHora(), actualizada.getHora());
        assertEquals(TipoServicio.PREMIUM, actualizada.getServicio());
        assertEquals(130.0, actualizada.getPrecio());
    }
}
