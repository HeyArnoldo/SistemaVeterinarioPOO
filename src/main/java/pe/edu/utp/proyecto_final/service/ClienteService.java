package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.ClienteDTO;
import pe.edu.utp.proyecto_final.models.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente crearCliente(ClienteDTO clienteDTO);
    List<Cliente> getAllClientes();
    Optional<Cliente> getClienteById(Long id);
    Optional<Cliente> getClienteByDni(String dni);
    void deleteCliente(Long id);
    Cliente updateCliente(Long id, ClienteDTO clienteDTO);
}