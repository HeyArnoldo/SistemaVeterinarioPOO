package pe.edu.utp.proyecto_final.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto_final.dto.ComprobantePagoDTO;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.ComprobantePago;
import pe.edu.utp.proyecto_final.repository.CitaRepository;
import pe.edu.utp.proyecto_final.repository.ComprobantePagoRepository;
import pe.edu.utp.proyecto_final.service.ComprobantePagoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobantePagoServiceImpl implements ComprobantePagoService {

    @Autowired
    private ComprobantePagoRepository comprobantePagoRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public ComprobantePago crearComprobantePago(ComprobantePagoDTO comprobantePagoDTO) {
        Cita cita = citaRepository.findById(comprobantePagoDTO.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // Por ahora usamos montoTotal del DTO como total del comprobante
        // y tipoComprobante como metodoPago o una observación similar.
        ComprobantePago comprobante = ComprobantePago.builder()
                .fechaEmision(comprobantePagoDTO.getFechaEmision() != null
                        ? comprobantePagoDTO.getFechaEmision()
                        : LocalDateTime.now())
                .subtotal(comprobantePagoDTO.getMontoTotal())
                .impuestos(0.0)
                .total(comprobantePagoDTO.getMontoTotal())
                .metodoPago(comprobantePagoDTO.getTipoComprobante())
                .cita(cita)
                .build();

        return comprobantePagoRepository.save(comprobante);
    }

    @Override
    public List<ComprobantePago> getAllComprobantesPago() {
        return comprobantePagoRepository.findAll();
    }

    @Override
    public Optional<ComprobantePago> getComprobantePagoById(Long id) {
        return comprobantePagoRepository.findById(id);
    }

    @Override
    public ComprobantePago updateComprobantePago(Long id, ComprobantePagoDTO comprobantePagoDTO) {
        ComprobantePago comprobante = comprobantePagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprobante de pago no encontrado"));

        Cita cita = citaRepository.findById(comprobantePagoDTO.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        comprobante.setFechaEmision(comprobantePagoDTO.getFechaEmision());
        comprobante.setSubtotal(comprobantePagoDTO.getMontoTotal());
        comprobante.setImpuestos(0.0);
        comprobante.setTotal(comprobantePagoDTO.getMontoTotal());
        comprobante.setMetodoPago(comprobantePagoDTO.getTipoComprobante());
        comprobante.setCita(cita);

        return comprobantePagoRepository.save(comprobante);
    }

    @Override
    public void deleteComprobantePago(Long id) {
        comprobantePagoRepository.deleteById(id);
    }
}
