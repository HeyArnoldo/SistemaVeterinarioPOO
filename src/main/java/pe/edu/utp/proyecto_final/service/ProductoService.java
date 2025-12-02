package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.ProductoDTO;
import pe.edu.utp.proyecto_final.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Producto crearProducto(ProductoDTO productoDTO);

    List<Producto> getAllProductos();

    Optional<Producto> getProductoById(Long id);

    Producto updateProducto(Long id, ProductoDTO productoDTO);

    void deleteProducto(Long id);
}

