package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.RecetaMedicaDTO;
import pe.edu.utp.proyecto_final.models.RecetaMedica;
import pe.edu.utp.proyecto_final.service.RecetaMedicaService;

import java.util.List;

@RestController
@RequestMapping("/api/recetas-medicas")
public class RecetaMedicaController {

    @Autowired
    private RecetaMedicaService recetaMedicaService;

    // 1. Obtener todas las recetas
    @GetMapping
    public ResponseEntity<List<RecetaMedica>> getAllRecetasMedicas() {
        return ResponseEntity.ok(recetaMedicaService.getAllRecetasMedicas());
    }

    // 2. Obtener receta por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecetaMedica> getRecetaMedicaById(@PathVariable Long id) {
        return recetaMedicaService.getRecetaMedicaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva receta
    @PostMapping
    public ResponseEntity<RecetaMedica> crearRecetaMedica(@RequestBody RecetaMedicaDTO recetaMedicaDTO) {
        RecetaMedica nueva = recetaMedicaService.crearRecetaMedica(recetaMedicaDTO);
        return ResponseEntity.ok(nueva);
    }

    // 4. Actualizar una receta existente
    @PutMapping("/{id}")
    public ResponseEntity<RecetaMedica> updateRecetaMedica(@PathVariable Long id,
                                                           @RequestBody RecetaMedicaDTO recetaMedicaDTO) {
        RecetaMedica actualizada = recetaMedicaService.updateRecetaMedica(id, recetaMedicaDTO);
        return ResponseEntity.ok(actualizada);
    }

    // 5. Eliminar una receta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecetaMedica(@PathVariable Long id) {
        recetaMedicaService.deleteRecetaMedica(id);
        return ResponseEntity.ok().build();
    }
}