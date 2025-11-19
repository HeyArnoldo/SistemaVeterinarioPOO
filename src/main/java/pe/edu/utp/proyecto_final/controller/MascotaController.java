package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.MascotaDTO;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.service.MascotaService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

    //POST / Crear una mascota
    @PostMapping
    public ResponseEntity<Mascota> registrarMascota(
            @RequestBody MascotaDTO mascotaDTO) {
            return ResponseEntity.ok(mascotaService.registrarMascota(mascotaDTO));
    }

    //GET / Leer todas las mascotas
    @GetMapping
    public ResponseEntity<java.util.List<Mascota>> getAllMascotas() {
        return ResponseEntity.ok(mascotaService.getAllMascotas());
    }

    //GET / Leer una mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
        return mascotaService.getAllMascotas().stream()
                .filter(mascota -> mascota.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETE / Borrar una mascota por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
        return ResponseEntity.ok().build();
    }

    //UPDATE / Actualizar una mascota
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> updateMascota(
            @PathVariable Long id,
            @RequestBody MascotaDTO mascotaDTO) {
        try {
            Mascota updatedMascota = mascotaService.updateMascota(id, mascotaDTO);
            return ResponseEntity.ok(updatedMascota);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
