package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.ClienteDTO;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        clienteDTO = new ClienteDTO();
        clienteDTO.setNombres("Juan");
        clienteDTO.setApellidos("Perez");
        clienteDTO.setTelefono("999999999");
        clienteDTO.setDni("12345678");
        clienteDTO.setEmail("juan@test.com");
        clienteDTO.setDireccion("Av. Siempre Viva");
    }

    @Test
    @DisplayName("crearCliente lanza error si el DNI ya existe")
    void crearClienteDebeFallarSiDniExiste() {
        when(clienteRepository.findByDni(clienteDTO.getDni())).thenReturn(Optional.of(new Cliente()));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.crearCliente(clienteDTO));

        assertTrue(ex.getMessage().contains("Ya existe un cliente"));
        verify(clienteRepository, never()).save(any());
    }

    @Test
    @DisplayName("crearCliente guarda el cliente con los datos del DTO")
    void crearClienteDebeGuardarDatosDelDto() {
        when(clienteRepository.findByDni(clienteDTO.getDni())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cliente resultado = clienteService.crearCliente(clienteDTO);

        ArgumentCaptor<Cliente> captor = ArgumentCaptor.forClass(Cliente.class);
        verify(clienteRepository).save(captor.capture());

        Cliente guardado = captor.getValue();
        assertEquals(clienteDTO.getNombres(), guardado.getNombres());
        assertEquals(clienteDTO.getApellidos(), guardado.getApellidos());
        assertEquals(clienteDTO.getDni(), guardado.getDni());
        assertEquals(clienteDTO.getTelefono(), guardado.getTelefono());
        assertEquals(clienteDTO.getEmail(), guardado.getEmail());
        assertEquals(clienteDTO.getDireccion(), guardado.getDireccion());
        assertEquals(guardado, resultado);
    }

    @Test
    @DisplayName("updateCliente lanza error si el id no existe")
    void updateClienteDebeFallarSiNoExiste() {
        when(clienteRepository.findById(10L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.updateCliente(10L, clienteDTO));

        assertTrue(ex.getMessage().contains("Cliente no encontrado"));
        verify(clienteRepository, never()).save(any());
    }

    @Test
    @DisplayName("updateCliente actualiza y guarda los campos")
    void updateClienteDebeActualizarCampos() {
        Cliente existente = new Cliente();
        existente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cliente actualizado = clienteService.updateCliente(1L, clienteDTO);

        assertEquals(clienteDTO.getNombres(), actualizado.getNombres());
        assertEquals(clienteDTO.getApellidos(), actualizado.getApellidos());
        assertEquals(clienteDTO.getDni(), actualizado.getDni());
        assertEquals(clienteDTO.getTelefono(), actualizado.getTelefono());
        assertEquals(clienteDTO.getEmail(), actualizado.getEmail());
        assertEquals(clienteDTO.getDireccion(), actualizado.getDireccion());
    }
}
