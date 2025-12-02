package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.ProductoDTO;
import pe.edu.utp.proyecto_final.models.Producto;
import pe.edu.utp.proyecto_final.repository.ProductoRepository;
import pe.edu.utp.proyecto_final.service.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(ProductoDTO productoDTO) {
        Producto producto = Producto.builder()
                .codigoBarras(productoDTO.getCodigoBarras())
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .marca(productoDTO.getMarca())
                .precio(productoDTO.getPrecio())
                .stock(productoDTO.getStock())
                .build();

        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto updateProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setCodigoBarras(productoDTO.getCodigoBarras());
        producto.setNombre(productoDTO.getNombre());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setMarca(productoDTO.getMarca());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());

        return productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}

