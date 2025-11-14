package pe.edu.utp.proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto_final.models.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDni(String dni); //BUSCAR CLIENTES POR DNI :D

    Optional<Cliente> findByEmail(String email); //BUSCAR CLIENTES POR EMAIL :D
}
