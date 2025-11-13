package pe.edu.utp.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto_final.dto.ClienteDTO;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    //Crear un cliente : POST http://localhost:8080/api/clientes
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteDTO clienteDTO){
        try{
            Cliente nuevoCliente = clienteService.crearCliente(clienteDTO);
            return ResponseEntity.ok(nuevoCliente);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    // LEER todos los clientes : GET http://localhost:8080/api/clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes(){
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    // LEER cliente por ID : GET http://localhost:8080/api/clientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // LEER cliente por DNI: GET http://localhost:8080/api/clientes/dni/{dni}
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> getClienteByDni(@PathVariable String dni) {
        return clienteService.getClienteByDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // BORRAR cliente: DELETE http://localhost:8080/api/clientes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }

    // ACTUALIZAR cliente: PUT http://localhost:8080/api/clientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente updatedCliente = clienteService.updateCliente(id, clienteDTO);
            return ResponseEntity.ok(updatedCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
