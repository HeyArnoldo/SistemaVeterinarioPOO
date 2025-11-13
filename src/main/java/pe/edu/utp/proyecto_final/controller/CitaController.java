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

    //GET / Leer todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        return ResponseEntity.ok(citaService.getAllCitas());
    }

    //DELETE / Borrar una cita por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.ok().build();
    }

    //GET / Leer una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long id) {
        return citaService.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //UPDATE / Actualizar una cita
    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(
            @PathVariable Long id,
            @RequestBody CitaRequestDTO citaDTO) {
        try {
            Cita updatedCita = citaService.updateCita(id, citaDTO);
            return ResponseEntity.ok(updatedCita);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
