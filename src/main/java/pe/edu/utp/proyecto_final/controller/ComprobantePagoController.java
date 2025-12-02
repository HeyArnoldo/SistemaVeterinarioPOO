package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.ComprobantePagoDTO;
import pe.edu.utp.proyecto_final.models.ComprobantePago;
import pe.edu.utp.proyecto_final.service.ComprobantePagoService;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes-pago")
public class ComprobantePagoController {

    @Autowired
    private ComprobantePagoService comprobantePagoService;

    @PostMapping
    public ResponseEntity<ComprobantePago> crearComprobantePago(@RequestBody ComprobantePagoDTO comprobantePagoDTO) {
        ComprobantePago nuevo = comprobantePagoService.crearComprobantePago(comprobantePagoDTO);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<ComprobantePago>> getAllComprobantesPago() {
        return ResponseEntity.ok(comprobantePagoService.getAllComprobantesPago());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobantePago> getComprobantePagoById(@PathVariable Long id) {
        return comprobantePagoService.getComprobantePagoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComprobantePago> updateComprobantePago(@PathVariable Long id,
                                                                 @RequestBody ComprobantePagoDTO comprobantePagoDTO) {
        ComprobantePago actualizado = comprobantePagoService.updateComprobantePago(id, comprobantePagoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprobantePago(@PathVariable Long id) {
        comprobantePagoService.deleteComprobantePago(id);
        return ResponseEntity.ok().build();
    }
}

