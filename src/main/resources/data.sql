-- =================================================================================
-- 1. TABLA CLIENTES
-- =================================================================================
INSERT INTO clientes (nombres, apellidos, dni, telefono, email, direccion) VALUES
('Juan', 'Perez', '12345678', '999888777', 'juan.perez@gmail.com', 'Av. Siempre Viva 123'),
('Maria', 'Gomez', '87654321', '999111222', 'maria.gomez@hotmail.com', 'Jr. Los Olivos 456'),
('Carlos', 'Lopez', '11223344', '987654321', 'carlos.lopez@yahoo.com', 'Calle Las Flores 789'),
('Ana', 'Martinez', '44332211', '912345678', 'ana.martinez@gmail.com', 'Av. Arequipa 1020'),
('Luis', 'Rodriguez', '55667788', '998877665', 'luis.rod@outlook.com', 'Jr. Puno 305'),
('Elena', 'Fernandez', '99887766', '923456789', 'elena.fer@gmail.com', 'Av. Brasil 200'),
('Pedro', 'Sanchez', '77441122', '978654312', 'pedro.sanchez@gmail.com', 'Calle Real 111'),
('Lucia', 'Diaz', '33221144', '965432198', 'lucia.diaz@yahoo.com', 'Av. La Marina 555'),
('Jorge', 'Torres', '66554433', '954321876', 'jorge.torres@hotmail.com', 'Jr. Cusco 888'),
( 'Sofia', 'Ramirez', '22113344', '943218765', 'sofia.ramirez@gmail.com', 'Av. Salaverry 999');

-- =================================================================================
-- 2. TABLA MASCOTAS
-- =================================================================================
INSERT INTO mascotas (nombre, especie, raza, edad, buen_comportamiento, cliente_id) VALUES
('Firulais', 'Perro', 'Labrador', 5, true, 1),
('Mimi', 'Gato', 'Persa', 3, true, 2),
('Max', 'Perro', 'Bulldog', 2, false, 3),
('Luna', 'Perro', 'Golden Retriever', 4, true, 4),
('Rocky', 'Perro', 'Pastor Aleman', 6, true, 5),
('Pelusa', 'Gato', 'Siames', 2, false, 6),
('Toby', 'Perro', 'Beagle', 1, true, 7),
('Simba', 'Gato', 'Angora', 4, true, 8),
('Coco', 'Perro', 'Poodle', 7, false, 9),
( 'Bella', 'Perro', 'Chihuahua', 3, true, 10);

-- =================================================================================
-- 3. TABLA CITAS
-- BASICO=40, COMPLETO=55, CEPILLADO=70, PELUQUERIA=90, ESPECIAL=110, PREMIUM=130
-- =================================================================================
INSERT INTO citas (fecha, hora, precio, servicio, cliente_id, mascota_id) VALUES
('2023-11-01', '10:00:00', 40.00, 'BASICO', 1, 1),
('2023-11-02', '11:30:00', 55.00, 'COMPLETO', 2, 2),
('2023-11-03', '09:00:00', 70.00, 'CEPILLADO', 3, 3),
('2023-11-04', '15:00:00', 90.00, 'PELUQUERIA', 4, 4),
('2023-11-05', '16:00:00', 110.00, 'ESPECIAL', 5, 5),
('2023-11-06', '10:30:00', 130.00, 'PREMIUM', 6, 6),
('2023-11-07', '12:00:00', 40.00, 'BASICO', 7, 7),
('2023-11-08', '14:00:00', 55.00, 'COMPLETO', 8, 8),
('2023-11-09', '11:00:00', 90.00, 'PELUQUERIA', 9, 9),
( '2023-11-10', '09:30:00', 40.00, 'BASICO', 10, 10);