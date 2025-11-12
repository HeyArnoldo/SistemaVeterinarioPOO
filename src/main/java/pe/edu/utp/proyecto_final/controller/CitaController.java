package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.CitaRequestDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.service.CitaService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    //ENDPOINT para AGENDAR
    @PostMapping("/agendar")
    public ResponseEntity<Cita> agendarCita(@RequestBody CitaRequestDTO requestDTO){
        try{
            Cita nuevaCita = citaService.agendarCita(requestDTO);
            return ResponseEntity.ok(nuevaCita);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    //ENDPOINT para CONSULTAR DISPONIBILIDAD
    @GetMapping("/disponibilidad")
    public ResponseEntity<Boolean> verificarDisponibilidad(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora)
        {
        return ResponseEntity.ok(!citaService.isHorarioOcupado(fecha, hora));
    }

    //ENDPOINT para VER AGENDA DEL DÍA
    @GetMapping("/agenda/{fecha}")
    public ResponseEntity<List<Cita>> getAgendaDelDia(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha)
        {
        return ResponseEntity.ok(citaService.getCitasPorFecha(fecha));
    }
}
