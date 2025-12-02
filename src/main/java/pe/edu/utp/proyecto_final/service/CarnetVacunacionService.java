package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.CarnetVacunacionDTO;
import pe.edu.utp.proyecto_final.models.CarnetVacunacion;

import java.util.List;
import java.util.Optional;

public interface CarnetVacunacionService {
    CarnetVacunacion crearCarnetVacunacion(CarnetVacunacionDTO carnetVacunacionDTO);

    List<CarnetVacunacion> getAllCarnetsVacunacion();

    Optional<CarnetVacunacion> getCarnetVacunacionById(Long id);

    CarnetVacunacion updateCarnetVacunacion(Long id, CarnetVacunacionDTO carnetVacunacionDTO);

    void deleteCarnetVacunacion(Long id);
}

