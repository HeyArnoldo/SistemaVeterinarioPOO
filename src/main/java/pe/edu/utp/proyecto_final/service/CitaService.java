package pe.edu.utp.proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.CitaRequestDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;
import pe.edu.utp.proyecto_final.repository.MascotaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private ServicioService servicioService;

    public Cita agendarCita(CitaRequestDTO requestDTO){
        //LOGICA DEL NEGOCIO:

        //1. VERIFICACIÓN DEL HORARIO
        if (isHorarioOcupado(requestDTO.getFecha(), requestDTO.getHora())){
            throw new RuntimeException("Error: Horario no disponible :c");
        }

        //2. BUSCAR CLIENTE Y MASCOTA
        Cliente cliente = clienteRepository.findById(requestDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Mascota mascota = mascotaRepository.findById(requestDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if(!mascota.getCliente().getId().equals(cliente.getId())){
            throw new RuntimeException("Error: La mascota no pertenece a ese cliente");
        }

        //3. REGISTRAR LA CITA :D
        Cita nuevaCita = new Cita();
        nuevaCita.setFecha(requestDTO.getFecha());
        nuevaCita.setHora(requestDTO.getHora());
        nuevaCita.setServicio(requestDTO.getServicio());
        nuevaCita.setCliente(cliente);
        nuevaCita.setMascota(mascota);
        nuevaCita.setPrecio(servicioService.getPrecio(requestDTO.getServicio()));

        return citaRepository.save(nuevaCita);
    }

    public boolean isHorarioOcupado(LocalDate fecha, LocalTime hora){
        return !citaRepository.findByFechaAndHora(fecha, hora).isEmpty();
    }

    public List<Cita> getCitasPorFecha(LocalDate fecha){
        return citaRepository.findByFecha(fecha);
    }

}
