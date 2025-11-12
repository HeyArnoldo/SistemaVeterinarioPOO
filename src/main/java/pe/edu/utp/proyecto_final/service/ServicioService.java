package pe.edu.utp.proyecto_final.service;

import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;

@Service
public class ServicioService {
    public double getPrecio(TipoServicio servicio){
        return switch (servicio) {
            case BASICO -> 40.0;
            case COMPLETO -> 55.0;
            case CEPILLADO -> 70.0;
            case PELUQUERIA -> 90.0;
            case ESPECIAL -> 110.0;
            case PREMIUM -> 130.0;
            default -> 0.0;
        };
    }
}
