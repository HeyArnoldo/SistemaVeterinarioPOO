package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.VeterinarioDTO;
import pe.edu.utp.proyecto_final.models.Veterinario;
import pe.edu.utp.proyecto_final.service.VeterinarioService;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<Veterinario> crearVeterinario(@RequestBody VeterinarioDTO veterinarioDTO) {
        Veterinario nuevo = veterinarioService.crearVeterinario(veterinarioDTO);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<Veterinario>> getAllVeterinarios() {
        return ResponseEntity.ok(veterinarioService.getAllVeterinarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> getVeterinarioById(@PathVariable Long id) {
        return veterinarioService.getVeterinarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cmvp/{cmvp}")
    public ResponseEntity<Veterinario> getVeterinarioByCmvp(@PathVariable String cmvp) {
        return veterinarioService.getVeterinarioByCmvp(cmvp)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> updateVeterinario(@PathVariable Long id,
                                                         @RequestBody VeterinarioDTO veterinarioDTO) {
        Veterinario actualizado = veterinarioService.updateVeterinario(id, veterinarioDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinario(@PathVariable Long id) {
        veterinarioService.deleteVeterinario(id);
        return ResponseEntity.ok().build();
    }
}

