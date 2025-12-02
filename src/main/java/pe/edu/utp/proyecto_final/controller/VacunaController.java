package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.VacunaDTO;
import pe.edu.utp.proyecto_final.models.Vacuna;
import pe.edu.utp.proyecto_final.service.VacunaService;

import java.util.List;

@RestController
@RequestMapping("/api/vacunas")
public class VacunaController {

    @Autowired
    private VacunaService vacunaService;

    @PostMapping
    public ResponseEntity<Vacuna> crearVacuna(@RequestBody VacunaDTO vacunaDTO) {
        Vacuna nueva = vacunaService.crearVacuna(vacunaDTO);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping
    public ResponseEntity<List<Vacuna>> getAllVacunas() {
        return ResponseEntity.ok(vacunaService.getAllVacunas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacuna> getVacunaById(@PathVariable Long id) {
        return vacunaService.getVacunaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacuna> updateVacuna(@PathVariable Long id,
                                               @RequestBody VacunaDTO vacunaDTO) {
        Vacuna actualizada = vacunaService.updateVacuna(id, vacunaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacuna(@PathVariable Long id) {
        vacunaService.deleteVacuna(id);
        return ResponseEntity.ok().build();
    }
}

