package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.CarnetVacunacionDTO;
import pe.edu.utp.proyecto_final.models.CarnetVacunacion;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.models.Vacuna;
import pe.edu.utp.proyecto_final.models.Veterinario;
import pe.edu.utp.proyecto_final.repository.CarnetVacunacionRepository;
import pe.edu.utp.proyecto_final.repository.MascotaRepository;
import pe.edu.utp.proyecto_final.repository.VacunaRepository;
import pe.edu.utp.proyecto_final.repository.VeterinarioRepository;
import pe.edu.utp.proyecto_final.service.impl.CarnetVacunacionServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarnetVacunacionServiceTest {

    @Mock
    private CarnetVacunacionRepository carnetVacunacionRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private VacunaRepository vacunaRepository;

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @InjectMocks
    private CarnetVacunacionServiceImpl carnetVacunacionService;

    private CarnetVacunacionDTO buildDTO() {
        CarnetVacunacionDTO dto = new CarnetVacunacionDTO();
        dto.setFechaAplicacion(LocalDate.of(2024, 10, 1));
        dto.setFechaProxima(LocalDate.of(2025, 10, 1));
        dto.setMascotaId(1L);
        dto.setVacunaId(2L);
        dto.setVeterinarioId(3L);
        return dto;
    }

    @Test
    @DisplayName("crearCarnetVacunacion lanza error si la mascota no existe")
    void crearCarnetDebeFallarSinMascota() {
        CarnetVacunacionDTO dto = buildDTO();
        when(mascotaRepository.findById(dto.getMascotaId())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> carnetVacunacionService.crearCarnetVacunacion(dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage()).contains("Mascota no encontrada");
        verify(carnetVacunacionRepository, never()).save(any());
    }

    @Test
    @DisplayName("crearCarnetVacunacion guarda correctamente los datos")
    void crearCarnetDebeGuardar() {
        CarnetVacunacionDTO dto = buildDTO();
        Mascota mascota = new Mascota();
        mascota.setId(1L);
        Vacuna vacuna = new Vacuna();
        vacuna.setId(2L);
        Veterinario vet = new Veterinario();
        vet.setId(3L);

        when(mascotaRepository.findById(dto.getMascotaId())).thenReturn(Optional.of(mascota));
        when(vacunaRepository.findById(dto.getVacunaId())).thenReturn(Optional.of(vacuna));
        when(veterinarioRepository.findById(dto.getVeterinarioId())).thenReturn(Optional.of(vet));
        when(carnetVacunacionRepository.save(any(CarnetVacunacion.class))).thenAnswer(inv -> inv.getArgument(0));

        CarnetVacunacion carnet = carnetVacunacionService.crearCarnetVacunacion(dto);

        assertEquals(dto.getFechaAplicacion(), carnet.getFechaAplicacion());
        assertEquals(dto.getFechaProxima(), carnet.getFechaProxima());
        assertEquals(mascota, carnet.getMascota());
        assertEquals(vacuna, carnet.getVacuna());
        assertEquals(vet, carnet.getVeterinario());
    }
}
