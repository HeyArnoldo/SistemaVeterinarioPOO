package pe.edu.utp.proyecto_final.service;

import pe.edu.utp.proyecto_final.dto.ComprobantePagoDTO;
import pe.edu.utp.proyecto_final.models.ComprobantePago;

import java.util.List;
import java.util.Optional;

public interface ComprobantePagoService {
    ComprobantePago crearComprobantePago(ComprobantePagoDTO comprobantePagoDTO);

    List<ComprobantePago> getAllComprobantesPago();

    Optional<ComprobantePago> getComprobantePagoById(Long id);

    ComprobantePago updateComprobantePago(Long id, ComprobantePagoDTO comprobantePagoDTO);

    void deleteComprobantePago(Long id);
}

