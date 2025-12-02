package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.ProveedorDTO;
import pe.edu.utp.proyecto_final.models.Proveedor;
import pe.edu.utp.proyecto_final.repository.ProveedorRepository;
import pe.edu.utp.proyecto_final.service.ProveedorService;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Proveedor crearProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = Proveedor.builder()
                .ruc(proveedorDTO.getRuc())
                .razonSocial(proveedorDTO.getRazonSocial())
                .telefono(proveedorDTO.getTelefono())
                .email(proveedorDTO.getEmail())
                .direccion(proveedorDTO.getDireccion())
                .build();

        return proveedorRepository.save(proveedor);
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> getProveedorById(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor updateProveedor(Long id, ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        proveedor.setRuc(proveedorDTO.getRuc());
        proveedor.setRazonSocial(proveedorDTO.getRazonSocial());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setEmail(proveedorDTO.getEmail());
        proveedor.setDireccion(proveedorDTO.getDireccion());

        return proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}

