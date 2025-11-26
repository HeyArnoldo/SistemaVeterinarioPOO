package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.ClienteDTO;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.repository.ClienteRepository;
import pe.edu.utp.proyecto_final.service.ClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(ClienteDTO clienteDTO) {
        // VALIDACIONES
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

    @Override
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long id){
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> getClienteByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado con ID: " + id));

        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDni(clienteDTO.getDni());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDireccion(clienteDTO.getDireccion());
        return clienteRepository.save(cliente);
    }
}