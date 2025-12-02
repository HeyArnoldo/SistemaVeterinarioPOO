package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.ProveedorDTO;
import pe.edu.utp.proyecto_final.models.Proveedor;
import pe.edu.utp.proyecto_final.repository.ProveedorRepository;
import pe.edu.utp.proyecto_final.service.impl.ProveedorServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    private ProveedorDTO buildDTO() {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setRuc("12345678901");
        dto.setRazonSocial("Proveedor S.A.");
        dto.setTelefono("987654321");
        dto.setEmail("contacto@proveedor.com");
        dto.setDireccion("Av. Siempre Viva 456");
        return dto;
    }

    @Test
    @DisplayName("crearProveedor guarda correctamente los datos del DTO")
    void crearProveedorDebeGuardarDatosDelDto() {
        ProveedorDTO dto = buildDTO();
        when(proveedorRepository.save(any(Proveedor.class))).thenAnswer(inv -> inv.getArgument(0));

        Proveedor resultado = proveedorService.crearProveedor(dto);

        assertEquals(dto.getRuc(), resultado.getRuc());
        assertEquals(dto.getRazonSocial(), resultado.getRazonSocial());
        assertEquals(dto.getTelefono(), resultado.getTelefono());
        assertEquals(dto.getEmail(), resultado.getEmail());
        assertEquals(dto.getDireccion(), resultado.getDireccion());
    }

    @Test
    @DisplayName("updateProveedor lanza error si el id no existe")
    void updateProveedorDebeFallarSiNoExiste() {
        ProveedorDTO dto = buildDTO();
        when(proveedorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> proveedorService.updateProveedor(1L, dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage()).contains("Proveedor no encontrado");
        verify(proveedorRepository, never()).save(any());
    }
}
