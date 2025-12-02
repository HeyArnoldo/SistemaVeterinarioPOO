package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.HistoriaClinicaDTO;
import pe.edu.utp.proyecto_final.models.HistoriaClinica;
import pe.edu.utp.proyecto_final.service.HistoriaClinicaService;

import java.util.List;

@RestController
@RequestMapping("/api/historias-clinicas")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @PostMapping
    public ResponseEntity<HistoriaClinica> crearHistoriaClinica(@RequestBody HistoriaClinicaDTO historiaClinicaDTO) {
        HistoriaClinica nueva = historiaClinicaService.crearHistoriaClinica(historiaClinicaDTO);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping
    public ResponseEntity<List<HistoriaClinica>> getAllHistoriasClinicas() {
        return ResponseEntity.ok(historiaClinicaService.getAllHistoriasClinicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaById(@PathVariable Long id) {
        return historiaClinicaService.getHistoriaClinicaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> updateHistoriaClinica(@PathVariable Long id,
                                                                 @RequestBody HistoriaClinicaDTO historiaClinicaDTO) {
        HistoriaClinica actualizada = historiaClinicaService.updateHistoriaClinica(id, historiaClinicaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaClinica(@PathVariable Long id) {
        historiaClinicaService.deleteHistoriaClinica(id);
        return ResponseEntity.ok().build();
    }
}

