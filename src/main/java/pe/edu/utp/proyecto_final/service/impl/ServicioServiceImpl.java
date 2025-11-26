package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;
import pe.edu.utp.proyecto_final.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Override
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