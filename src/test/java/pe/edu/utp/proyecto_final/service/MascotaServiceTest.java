package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.ClienteDTO;
import pe.edu.utp.proyecto_final.dto.MascotaDTO;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;
import pe.edu.utp.proyecto_final.repository.MascotaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private MascotaService mascotaService;

    @InjectMocks
    private ClienteService clienteService;

    private MascotaDTO mascotaDTO;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        mascotaDTO = new MascotaDTO();
        mascotaDTO.setNombre("Firulais");
        mascotaDTO.setRaza("Mestizo");
        mascotaDTO.setEspecie("Perro");
        mascotaDTO.setEdad(3);
        mascotaDTO.setBuenComportamiento(true);
        mascotaDTO.setClienteId(1L);

        clienteDTO = new ClienteDTO();
        clienteDTO.setNombres("Juan");
        clienteDTO.setApellidos("Perez");
        clienteDTO.setDni("12345678");
        clienteDTO.setEmail("juan.perez@gmail.com");
        clienteDTO.setTelefono("987654321");
        clienteDTO.setDireccion("Av. Siempre Viva 123");
    }

    @Test
    @DisplayName("registrarMascota lanza error cuando el cliente no existe")
    void registrarMascotaDebeFallarCuandoNoHayCliente() {
        when(clienteRepository.findById(mascotaDTO.getClienteId())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> mascotaService.registrarMascota(mascotaDTO));

        assertTrue(ex.getMessage().contains("Cliente no encontrado"));
        verify(mascotaRepository, never()).save(any());
    }

    @Test
    @DisplayName("registrarMascota crea la mascota asociada al cliente")
    void registrarMascotaDebeGuardarMascota() {
        Cliente cliente = new Cliente();
        cliente.setNombres("Juan");
        cliente.setApellidos("Perez");
        cliente.setDni("12345678");
        cliente.setEmail("juan.perez@gmail.com");
        cliente.setTelefono("987654321");
        cliente.setDireccion("Av. Siempre Viva 123");

        when(clienteRepository.findById(mascotaDTO.getClienteId())).thenReturn(Optional.of(cliente));
        when(mascotaRepository.save(any(Mascota.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Mascota mascotaGuardada = mascotaService.registrarMascota(mascotaDTO);

        assertEquals(mascotaDTO.getNombre(), mascotaGuardada.getNombre());
        assertEquals(mascotaDTO.getRaza(), mascotaGuardada.getRaza());
        assertEquals(mascotaDTO.getEspecie(), mascotaGuardada.getEspecie());
        assertEquals(mascotaDTO.getEdad(), mascotaGuardada.getEdad());
        assertEquals(cliente, mascotaGuardada.getCliente());
    }

    @Test
    @DisplayName("updateMascota lanza error si la mascota no existe")
    void updateMascotaDebeFallarCuandoNoExiste() {
        when(mascotaRepository.findById(5L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> mascotaService.updateMascota(5L, mascotaDTO));

        assertTrue(ex.getMessage().contains("Mascota no encontrada"));
    }

    @Test
    @DisplayName("updateMascota actualiza los datos básicos")
    void updateMascotaDebeActualizarDatos() {
        Mascota mascota = new Mascota();
        mascota.setId(2L);
        when(mascotaRepository.findById(2L)).thenReturn(Optional.of(mascota));
        when(mascotaRepository.save(any(Mascota.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Mascota actualizada = mascotaService.updateMascota(2L, mascotaDTO);

        assertEquals(mascotaDTO.getNombre(), actualizada.getNombre());
        assertEquals(mascotaDTO.getRaza(), actualizada.getRaza());
        assertEquals(mascotaDTO.getEspecie(), actualizada.getEspecie());
        assertEquals(mascotaDTO.getEdad(), actualizada.getEdad());
    }
}
