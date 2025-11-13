package pe.edu.utp.proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.ClienteDTO;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //CREAR CLIENTE
    public Cliente crearCliente(ClienteDTO clienteDTO) {
        //VALIDACIONES
        if(clienteRepository.findByDni(clienteDTO.getDni()).isPresent()){
            throw new RuntimeException("Error: Ya existe un cliente con ese DNI proporcionado.");
        }

        Cliente cliente = new Cliente();
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDni(clienteDTO.getDni());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDireccion(clienteDTO.getDireccion());

        return clienteRepository.save(cliente);
    }

    //LEER: Obtener todos los clientes
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    //LEER: Obtener cliente por ID
    public Optional<Cliente> getClienteById(Long id){
        return clienteRepository.findById(id);
    }

    //LEER: Obtener cliente por DNI
    public Optional<Cliente> getClienteByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    //BORRAR CLIENTE
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    //ACTUALIZAR CLIENTE
    public Cliente updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isEmpty()) {
            throw new RuntimeException("Error: Cliente no encontrado con ID: " + id);
        }

        Cliente cliente = optionalCliente.get();
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDni(clienteDTO.getDni());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDireccion(clienteDTO.getDireccion());
        return clienteRepository.save(cliente);
    }


}
