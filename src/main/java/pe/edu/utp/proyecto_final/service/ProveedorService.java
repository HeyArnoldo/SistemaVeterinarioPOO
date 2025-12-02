package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.ProveedorDTO;
import pe.edu.utp.proyecto_final.models.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    Proveedor crearProveedor(ProveedorDTO proveedorDTO);

    List<Proveedor> getAllProveedores();

    Optional<Proveedor> getProveedorById(Long id);

    Proveedor updateProveedor(Long id, ProveedorDTO proveedorDTO);

    void deleteProveedor(Long id);
}

