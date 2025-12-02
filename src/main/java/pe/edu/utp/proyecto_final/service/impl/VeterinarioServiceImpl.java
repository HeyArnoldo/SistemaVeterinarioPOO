package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.VeterinarioDTO;
import pe.edu.utp.proyecto_final.models.Veterinario;
import pe.edu.utp.proyecto_final.repository.VeterinarioRepository;
import pe.edu.utp.proyecto_final.service.VeterinarioService;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Override
    public Veterinario crearVeterinario(VeterinarioDTO veterinarioDTO) {
        Veterinario veterinario = Veterinario.builder()
                .nombres(veterinarioDTO.getNombres())
                .apellidos(veterinarioDTO.getApellidos())
                .cmvp(veterinarioDTO.getCmvp())
                .especialidad(veterinarioDTO.getEspecialidad())
                .telefono(veterinarioDTO.getTelefono())
                .email(veterinarioDTO.getEmail())
                .build();

        return veterinarioRepository.save(veterinario);
    }

    @Override
    public List<Veterinario> getAllVeterinarios() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Optional<Veterinario> getVeterinarioById(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    public Optional<Veterinario> getVeterinarioByCmvp(String cmvp) {
        return veterinarioRepository.findByCmvp(cmvp);
    }

    @Override
    public Veterinario updateVeterinario(Long id, VeterinarioDTO veterinarioDTO) {
        Veterinario veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        veterinario.setNombres(veterinarioDTO.getNombres());
        veterinario.setApellidos(veterinarioDTO.getApellidos());
        veterinario.setCmvp(veterinarioDTO.getCmvp());
        veterinario.setEspecialidad(veterinarioDTO.getEspecialidad());
        veterinario.setTelefono(veterinarioDTO.getTelefono());
        veterinario.setEmail(veterinarioDTO.getEmail());

        return veterinarioRepository.save(veterinario);
    }

    @Override
    public void deleteVeterinario(Long id) {
        veterinarioRepository.deleteById(id);
    }
}

