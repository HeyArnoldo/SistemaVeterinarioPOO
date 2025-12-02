package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.VeterinarioDTO;
import pe.edu.utp.proyecto_final.models.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioService {
    Veterinario crearVeterinario(VeterinarioDTO veterinarioDTO);

    List<Veterinario> getAllVeterinarios();

    Optional<Veterinario> getVeterinarioById(Long id);

    Optional<Veterinario> getVeterinarioByCmvp(String cmvp);

    Veterinario updateVeterinario(Long id, VeterinarioDTO veterinarioDTO);

    void deleteVeterinario(Long id);
}

