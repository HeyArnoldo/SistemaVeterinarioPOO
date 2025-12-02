package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.proyecto_final.dto.ProductoDTO;
import pe.edu.utp.proyecto_final.models.Producto;
import pe.edu.utp.proyecto_final.repository.ProductoRepository;
import pe.edu.utp.proyecto_final.service.impl.ProductoServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private ProductoDTO buildDTO() {
        ProductoDTO dto = new ProductoDTO();
        dto.setCodigoBarras("ABC123");
        dto.setNombre("Juguete perro");
        dto.setCategoria("Juguetes");
        dto.setMarca("PetBrand");
        dto.setPrecio(20.0);
        dto.setStock(10);
        return dto;
    }

    @Test
    @DisplayName("crearProducto guarda correctamente los datos del DTO")
    void crearProductoDebeGuardarDatosDelDto() {
        ProductoDTO dto = buildDTO();
        when(productoRepository.save(any(Producto.class))).thenAnswer(inv -> inv.getArgument(0));

        Producto resultado = productoService.crearProducto(dto);

        assertEquals(dto.getCodigoBarras(), resultado.getCodigoBarras());
        assertEquals(dto.getNombre(), resultado.getNombre());
        assertEquals(dto.getCategoria(), resultado.getCategoria());
        assertEquals(dto.getMarca(), resultado.getMarca());
        assertEquals(dto.getPrecio(), resultado.getPrecio());
        assertEquals(dto.getStock(), resultado.getStock());
    }

    @Test
    @DisplayName("updateProducto lanza error si el id no existe")
    void updateProductoDebeFallarSiNoExiste() {
        ProductoDTO dto = buildDTO();
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productoService.updateProducto(1L, dto));

        org.assertj.core.api.Assertions.assertThat(ex.getMessage()).contains("Producto no encontrado");
        verify(productoRepository, never()).save(any());
    }
}
