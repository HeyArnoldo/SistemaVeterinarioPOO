package pe.edu.utp.proyecto_final;

import org.junit.jupiter.api.Test;
import pe.edu.utp.proyecto_final.models.Cita;
import pe.edu.utp.proyecto_final.models.Cliente;
import pe.edu.utp.proyecto_final.models.Mascota;
import pe.edu.utp.proyecto_final.models.enums.TipoServicio;
import pe.edu.utp.proyecto_final.service.ServicioService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Prueba de demostración para mostrar cómo se relacionan los objetos principales
 * del sistema (Cliente, Mascota y Cita) y cómo se obtienen los precios de los
 * distintos servicios de la veterinaria.
 */
public class PruebasDeVeterinaria {

    private final ServicioService servicioService = new ServicioService();

    @Test
    void demoDelSistemaVeterinario() {
        System.out.println("====== INICIO DE PRUEBA DE DEMO PARA LA VETERINARIA ======\n");

        Cliente cliente = crearClienteEjemplo();
        Mascota mascota = crearMascotaEjemplo(cliente);
        Cita cita = crearCitaEjemplo(cliente, mascota, TipoServicio.PREMIUM);

        // Aseguramos las referencias bidireccionales para la demostración en memoria
        cliente.setMascotas(List.of(mascota));
        cliente.setCitas(List.of(cita));
        mascota.setCitas(List.of(cita));

        System.out.println("--- Cliente registrado ---");
        imprimirCliente(cliente);

        System.out.println("\n--- Mascota asociada al cliente ---");
        imprimirMascota(mascota);

        System.out.println("\n--- Cita agendada para la mascota ---");
        imprimirCita(cita);

        System.out.println("\n--- Tabla de precios de servicios ---");
        mostrarPrecios();

        System.out.println("\n====== FIN DE LA DEMO ======");
    }

    private Cliente crearClienteEjemplo() {
        Cliente cliente = new Cliente();
        cliente.setNombres("Luisa");
        cliente.setApellidos("Campos Perez");
        cliente.setDni("88888888");
        cliente.setTelefono("999-888-777");
        cliente.setEmail("luisa.campos@example.com");
        cliente.setDireccion("Av. Las Flores 123, Lima");
        return cliente;
    }

    private Mascota crearMascotaEjemplo(Cliente cliente) {
        Mascota mascota = new Mascota();
        mascota.setNombre("Rocky");
        mascota.setEspecie("Canino");
        mascota.setRaza("Husky Siberiano");
        mascota.setEdad(3);
        mascota.setBuenComportamiento(true);
        mascota.setCliente(cliente);
        return mascota;
    }

    private Cita crearCitaEjemplo(Cliente cliente, Mascota mascota, TipoServicio servicio) {
        Cita cita = new Cita();
        cita.setFecha(LocalDate.now().plusDays(1));
        cita.setHora(LocalTime.of(10, 30));
        cita.setServicio(servicio);
        cita.setCliente(cliente);
        cita.setMascota(mascota);
        cita.setPrecio(servicioService.getPrecio(servicio));
        return cita;
    }

    private void imprimirCliente(Cliente cliente) {
        System.out.printf("Nombre completo : %s %s%n", cliente.getNombres(), cliente.getApellidos());
        System.out.printf("DNI             : %s%n", cliente.getDni());
        System.out.printf("Contacto        : %s | %s%n", cliente.getTelefono(), cliente.getEmail());
        System.out.printf("Dirección       : %s%n", cliente.getDireccion());
        System.out.printf("Mascotas        : %d registrada(s)%n", cliente.getMascotas() != null ? cliente.getMascotas().size() : 0);
        System.out.printf("Citas agendadas : %d%n", cliente.getCitas() != null ? cliente.getCitas().size() : 0);
    }

    private void imprimirMascota(Mascota mascota) {
        System.out.printf("Nombre          : %s%n", mascota.getNombre());
        System.out.printf("Especie / Raza  : %s / %s%n", mascota.getEspecie(), mascota.getRaza());
        System.out.printf("Edad            : %d años%n", mascota.getEdad());
        System.out.printf("Buen comportamiento: %s%n", Boolean.TRUE.equals(mascota.getBuenComportamiento()) ? "Sí" : "No");
        System.out.printf("Dueño           : %s %s%n", mascota.getCliente().getNombres(), mascota.getCliente().getApellidos());
    }

    private void imprimirCita(Cita cita) {
        System.out.printf("Fecha y hora    : %s %s%n", cita.getFecha(), cita.getHora());
        System.out.printf("Servicio        : %s%n", cita.getServicio());
        System.out.printf("Precio          : S/ %.2f%n", cita.getPrecio());
        System.out.printf("Cliente         : %s %s%n", cita.getCliente().getNombres(), cita.getCliente().getApellidos());
        System.out.printf("Mascota         : %s%n", cita.getMascota().getNombre());
    }

    private void mostrarPrecios() {
        for (TipoServicio servicio : TipoServicio.values()) {
            System.out.printf("%s : S/ %.2f%n", servicio, servicioService.getPrecio(servicio));
        }
    }
}