package pe.edu.utp.proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.MascotaDTO;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;
import pe.edu.utp.proyecto_final.repository.MascotaRepository;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    //CREAR MASCOTA
    public Mascota registrarMascota(MascotaDTO mascotaDTO){
        //1. Buscar al cliente dueño
        Cliente cliente = clienteRepository.findById(mascotaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado con ID proporcionado."));
        //2. Crear la mascota
        Mascota mascota = new Mascota();
        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setRaza(mascotaDTO.getRaza());
        mascota.setEspecie(mascota.getEspecie());
        mascota.setEdad(mascotaDTO.getEdad());
        mascota.setCliente(cliente);

        //3. Asignar la mascota al cliente
        mascota.setCliente(cliente);

        return mascotaRepository.save(mascota);
    }

    public List<Mascota> getAllMascotas(){
        return mascotaRepository.findAll();
    }

    //BORRAR MASCOTA
    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    //ACTUALIZAR MASCOTA
    public Mascota updateMascota(Long id, MascotaDTO mascotaDTO) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Mascota no encontrada con ID proporcionado."));
        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setRaza(mascotaDTO.getRaza());
        mascota.setEspecie(mascotaDTO.getEspecie());
        mascota.setEdad(mascotaDTO.getEdad());
        return mascotaRepository.save(mascota);
    }
}
