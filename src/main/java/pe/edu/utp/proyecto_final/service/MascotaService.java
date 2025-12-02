package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.MascotaDTO;
import pe.edu.utp.proyecto_final.models.Mascota;

import java.util.List;

public interface MascotaService {
    Mascota registrarMascota(MascotaDTO mascotaDTO);

    List<Mascota> getAllMascotas();

    void deleteMascota(Long id);

    Mascota updateMascota(Long id, MascotaDTO mascotaDTO);
}