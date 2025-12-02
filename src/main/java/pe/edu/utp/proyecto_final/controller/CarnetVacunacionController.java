package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.CarnetVacunacionDTO;
import pe.edu.utp.proyecto_final.models.CarnetVacunacion;
import pe.edu.utp.proyecto_final.service.CarnetVacunacionService;

import java.util.List;

@RestController
@RequestMapping("/api/carnets-vacunacion")
public class CarnetVacunacionController {

    @Autowired
    private CarnetVacunacionService carnetVacunacionService;

    @PostMapping
    public ResponseEntity<CarnetVacunacion> crearCarnetVacunacion(@RequestBody CarnetVacunacionDTO carnetVacunacionDTO) {
        CarnetVacunacion nuevo = carnetVacunacionService.crearCarnetVacunacion(carnetVacunacionDTO);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<CarnetVacunacion>> getAllCarnetsVacunacion() {
        return ResponseEntity.ok(carnetVacunacionService.getAllCarnetsVacunacion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarnetVacunacion> getCarnetVacunacionById(@PathVariable Long id) {
        return carnetVacunacionService.getCarnetVacunacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarnetVacunacion> updateCarnetVacunacion(@PathVariable Long id,
                                                                   @RequestBody CarnetVacunacionDTO carnetVacunacionDTO) {
        CarnetVacunacion actualizado = carnetVacunacionService.updateCarnetVacunacion(id, carnetVacunacionDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarnetVacunacion(@PathVariable Long id) {
        carnetVacunacionService.deleteCarnetVacunacion(id);
        return ResponseEntity.ok().build();
    }
}

