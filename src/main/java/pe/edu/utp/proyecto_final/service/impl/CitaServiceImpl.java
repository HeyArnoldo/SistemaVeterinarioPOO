package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.CitaRequestDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;
import pe.edu.utp.proyecto_final.repository.MascotaRepository;
import pe.edu.utp.proyecto_final.service.CitaService;
import pe.edu.utp.proyecto_final.service.ServicioService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private ServicioService servicioService; // Spring inyectará ServicioServiceImpl automáticamente

    @Override
    public Cita agendarCita(CitaRequestDTO requestDTO) {
        // 1. VERIFICACIÓN DEL HORARIO
        if (isHorarioOcupado(requestDTO.getFecha(), requestDTO.getHora())) {
            throw new RuntimeException("Error: Horario no disponible :c");
        }

        // 2. BUSCAR CLIENTE Y MASCOTA
        Cliente cliente = clienteRepository.findById(requestDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Mascota mascota = mascotaRepository.findById(requestDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if (!mascota.getCliente().getId().equals(cliente.getId())) {
            throw new RuntimeException("Error: La mascota no pertenece a ese cliente");
        }

        // 3. REGISTRAR LA CITA :D
        Cita nuevaCita = new Cita();
        nuevaCita.setFecha(requestDTO.getFecha());
        nuevaCita.setHora(requestDTO.getHora());
        nuevaCita.setServicio(requestDTO.getServicio());
        nuevaCita.setCliente(cliente);
        nuevaCita.setMascota(mascota);
        // Usamos el servicio (interfaz) para obtener el precio
        nuevaCita.setPrecio(servicioService.getPrecio(requestDTO.getServicio()));

        return citaRepository.save(nuevaCita);
    }

    @Override
    public boolean isHorarioOcupado(LocalDate fecha, LocalTime hora) {
        return !citaRepository.findByFechaAndHora(fecha, hora).isEmpty();
    }

    @Override
    public List<Cita> getCitasPorFecha(LocalDate fecha) {
        return citaRepository.findByFecha(fecha);
    }

    @Override
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @Override
    public void deleteCita(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public Optional<Cita> getCitaById(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita updateCita(Long id, CitaRequestDTO citaDTO) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Cita no encontrada con ID proporcionado."));

        // VERIFICAR SI EL NUEVO HORARIO ESTÁ DISPONIBLE
        if (isHorarioOcupado(citaDTO.getFecha(), citaDTO.getHora()) &&
                !(cita.getFecha().equals(citaDTO.getFecha()) && cita.getHora().equals(citaDTO.getHora()))) {
            throw new RuntimeException("Error: Horario no disponible :c");
        }

        cita.setFecha(citaDTO.getFecha());
        cita.setHora(citaDTO.getHora());
        cita.setServicio(citaDTO.getServicio());
        cita.setPrecio(servicioService.getPrecio(citaDTO.getServicio()));

        return citaRepository.save(cita);
    }
}