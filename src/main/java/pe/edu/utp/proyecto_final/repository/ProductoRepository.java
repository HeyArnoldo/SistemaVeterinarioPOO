package pe.edu.utp.proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto_final.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
