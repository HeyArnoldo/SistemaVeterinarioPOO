package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.MedicamentoDTO;
import pe.edu.utp.proyecto_final.models.Medicamento;
import pe.edu.utp.proyecto_final.service.MedicamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    // 1. Obtener todos los medicamentos
    @GetMapping
    public ResponseEntity<List<Medicamento>> getAllMedicamentos() {
        return ResponseEntity.ok(medicamentoService.getAllMedicamentos());
    }

    // 2. Obtener medicamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getMedicamentoById(@PathVariable Long id) {
        return medicamentoService.getMedicamentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear un nuevo medicamento
    @PostMapping
    public ResponseEntity<Medicamento> crearMedicamento(@RequestBody MedicamentoDTO medicamentoDTO) {
        Medicamento nuevo = medicamentoService.crearMedicamento(medicamentoDTO);
        return ResponseEntity.ok(nuevo);
    }

    // 4. Actualizar un medicamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@PathVariable Long id,
                                                         @RequestBody MedicamentoDTO medicamentoDTO) {
        Medicamento actualizado = medicamentoService.updateMedicamento(id, medicamentoDTO);
        return ResponseEntity.ok(actualizado);
    }

    // 5. Eliminar un medicamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        medicamentoService.deleteMedicamento(id); // Acción primero
        return ResponseEntity.ok().build();       // Respuesta después
    }
}