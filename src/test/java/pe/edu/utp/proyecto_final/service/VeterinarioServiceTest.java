package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.VeterinarioDTO;
import pe.edu.utp.proyecto_final.models.Veterinario;
import pe.edu.utp.proyecto_final.repository.VeterinarioRepository;
import pe.edu.utp.proyecto_final.service.impl.VeterinarioServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeterinarioServiceTest {

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @InjectMocks
    private VeterinarioServiceImpl veterinarioService;

    private VeterinarioDTO buildDTO() {
        VeterinarioDTO dto = new VeterinarioDTO();
        dto.setNombres("Ana");
        dto.setApellidos("Lopez");
        dto.setCmvp("CMVP12345");
        dto.setEspecialidad("Dermatología");
        dto.setTelefono("999888777");
        dto.setEmail("ana@vet.com");
        return dto;
    }

    @Test
    @DisplayName("crearVeterinario guarda correctamente los datos del DTO")
    void crearVeterinarioDebeGuardarDatosDelDto() {
        VeterinarioDTO dto = buildDTO();
        when(veterinarioRepository.save(any(Veterinario.class))).thenAnswer(inv -> inv.getArgument(0));

        Veterinario resultado = veterinarioService.crearVeterinario(dto);

        assertEquals(dto.getNombres(), resultado.getNombres());
        assertEquals(dto.getApellidos(), resultado.getApellidos());
        assertEquals(dto.getCmvp(), resultado.getCmvp());
        assertEquals(dto.getEspecialidad(), resultado.getEspecialidad());
        assertEquals(dto.getTelefono(), resultado.getTelefono());
        assertEquals(dto.getEmail(), resultado.getEmail());
    }

    @Test
    @DisplayName("updateVeterinario lanza error si el id no existe")
    void updateVeterinarioDebeFallarSiNoExiste() {
        VeterinarioDTO dto = buildDTO();
        when(veterinarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> veterinarioService.updateVeterinario(1L, dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage()).contains("Veterinario no encontrado");
        verify(veterinarioRepository, never()).save(any());
    }
}
