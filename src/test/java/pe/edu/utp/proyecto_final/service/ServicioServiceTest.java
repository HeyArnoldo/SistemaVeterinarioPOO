package pe.edu.utp.proyecto_final.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;
import pe.edu.utp.proyecto_final.service.impl.ServicioServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioServiceTest {

    private final ServicioService servicioService = new ServicioServiceImpl();

    @Test
    @DisplayName("getPrecio retorna el monto configurado para cada servicio")
    void getPrecioDebeRetornarMontoCorrecto() {
        assertEquals(40.0, servicioService.getPrecio(TipoServicio.BASICO));
        assertEquals(55.0, servicioService.getPrecio(TipoServicio.COMPLETO));
        assertEquals(70.0, servicioService.getPrecio(TipoServicio.CEPILLADO));
        assertEquals(90.0, servicioService.getPrecio(TipoServicio.PELUQUERIA));
        assertEquals(110.0, servicioService.getPrecio(TipoServicio.ESPECIAL));
        assertEquals(130.0, servicioService.getPrecio(TipoServicio.PREMIUM));
    }
}
