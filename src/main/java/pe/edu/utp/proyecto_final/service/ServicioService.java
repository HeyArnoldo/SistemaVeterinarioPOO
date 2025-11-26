package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.models.enums.TipoServicio;

public interface ServicioService {
    double getPrecio(TipoServicio servicio);
}