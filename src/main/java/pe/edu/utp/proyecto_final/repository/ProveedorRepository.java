package pe.edu.utp.proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto_final.models.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
