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
-- =================================================================================
-- 4. TABLA VETERINARIOS (Personal Médico)
-- =================================================================================
INSERT INTO veterinarios (nombres, apellidos, cmvp, especialidad, telefono, email) VALUES
('Ricardo', 'Mendoza', 'CMVP-001', 'Medicina General', '991122334', 'ricardo.vet@gmail.com'),
('Laura', 'Salazar', 'CMVP-002', 'Cirugía', '992233445', 'laura.cirugia@gmail.com'),
('Fernando', 'Vargas', 'CMVP-003', 'Dermatología', '993344556', 'fernando.derma@hotmail.com'),
('Patricia', 'Rios', 'CMVP-004', 'Cardiología', '994455667', 'patty.rios@outlook.com'),
('Roberto', 'Castillo', 'CMVP-005', 'Medicina General', '995566778', 'roberto.vet@yahoo.com'),
('Carmen', 'Villanueva', 'CMVP-006', 'Odontología', '996677889', 'carmen.dentist@gmail.com'),
('Hugo', 'Chavez', 'CMVP-007', 'Traumatología', '997788990', 'hugo.trauma@gmail.com'),
('Monica', 'Paredes', 'CMVP-008', 'Medicina Interna', '998899001', 'monica.paredes@hotmail.com'),
('Esteban', 'Quispe', 'CMVP-009', 'Animales Exóticos', '999900112', 'esteban.exotic@gmail.com'),
('Diana', 'Flores', 'CMVP-010', 'Oftalmología', '990011223', 'diana.ojos@outlook.com');

-- ACTUALIZAMOS LAS CITAS EXISTENTES PARA ASIGNARLES UN VETERINARIO
UPDATE citas SET veterinario_id = 1 WHERE id = 1;
UPDATE citas SET veterinario_id = 2 WHERE id = 2;
UPDATE citas SET veterinario_id = 3 WHERE id = 3;
UPDATE citas SET veterinario_id = 4 WHERE id = 4;
UPDATE citas SET veterinario_id = 5 WHERE id = 5;
UPDATE citas SET veterinario_id = 6 WHERE id = 6;
UPDATE citas SET veterinario_id = 7 WHERE id = 7;
UPDATE citas SET veterinario_id = 8 WHERE id = 8;
UPDATE citas SET veterinario_id = 9 WHERE id = 9;
UPDATE citas SET veterinario_id = 10 WHERE id = 10;

-- =================================================================================
-- 5. TABLA PROVEEDORES (Abastecimiento)
-- =================================================================================
INSERT INTO proveedores (ruc, razon_social, telefono, email, direccion) VALUES
('20100011122', 'Distribuidora VetPeru SAC', '012223333', 'ventas@vetperu.com', 'Av. Industrial 100'),
('20200022233', 'Laboratorios Sanidad Animal', '013334444', 'contacto@sanidadanimal.com', 'Calle Los Químicos 200'),
('20300033344', 'Importaciones PetShop', '014445555', 'info@petshopimport.com', 'Av. La Molina 300'),
('20400044455', 'Alimentos Balanceados SA', '015556666', 'pedidos@alimentosb.com', 'Panamericana Sur Km 20'),
('20500055566', 'Accesorios y Juguetes EIRL', '016667777', 'ventas@juguetespet.com', 'Jr. Gamarra 500'),
('20600066677', 'Farmavet Global', '017778888', 'contacto@farmavet.com', 'Av. Javier Prado 1200'),
('20700077788', 'Equipos Médicos Veterinarios', '018889999', 'info@equiposvet.com', 'Av. Arequipa 400'),
('20800088899', 'Higiene Total SAC', '019990000', 'limpieza@higuienetotal.com', 'Calle Las Begonias 150'),
('20900099900', 'Nutrición Animal Pro', '011112222', 'nutricion@proanimal.com', 'Av. El Ejército 800'),
('20111100011', 'Insumos Quirúrgicos Vet', '012229999', 'ventas@insumosq.com', 'Jr. Unión 100');

-- =================================================================================
-- 6. TABLA VACUNAS (Catálogo)
-- =================================================================================
INSERT INTO vacunas (nombre, laboratorio, lote, dias_proxima_dosis) VALUES
('Quíntuple Canina', 'Zoetis', 'L-1001', 365),
('Triple Felina', 'Virbac', 'L-1002', 365),
('Antirrábica', 'Laboratorios Sanidad', 'L-1003', 365),
('Tos de las perreras', 'MSD Animal Health', 'L-1004', 180),
('Parvovirus', 'Vanguard', 'L-1005', 365),
('Leucemia Felina', 'Nobivac', 'L-1006', 365),
('Giardia', 'Zoetis', 'L-1007', 365),
('Distemper', 'Merck', 'L-1008', 365),
('Sextuple', 'Virbac', 'L-1009', 365),
('Desparasitación Interna', 'Bayer', 'L-1010', 90);

-- =================================================================================
-- 7. TABLA MEDICAMENTOS (Farmacia)
-- =================================================================================
INSERT INTO medicamentos (nombre, principio_activo, precio_unitario, stock, indicaciones) VALUES
('Amoxicilina 500mg', 'Amoxicilina', 2.50, 100, 'Antibiótico de amplio espectro'),
('Meloxicam 2mg', 'Meloxicam', 3.00, 50, 'Antiinflamatorio y analgésico'),
('Drontal Plus', 'Praziquantel', 15.00, 200, 'Desparasitante interno perros'),
('NexGard', 'Afoxolaner', 45.00, 80, 'Pulgas y garrapatas masticable'),
('Tramadol Gotas', 'Tramadol', 25.00, 30, 'Analgésico para dolor fuerte'),
('Ranitidina', 'Ranitidina', 1.50, 60, 'Protector gástrico'),
('Oto-Cleans', 'Ácido Salicílico', 35.00, 40, 'Limpiador de oídos'),
('Colirio Vet', 'Gentamicina', 20.00, 25, 'Gotas para infección ocular'),
('Shampoo Medicado', 'Clorhexidina', 28.00, 45, 'Para problemas de piel'),
('Vitamina Can', 'Complejo B', 30.00, 70, 'Suplemento vitamínico');

-- =================================================================================
-- 8. TABLA PRODUCTOS (Tienda)
-- =================================================================================
INSERT INTO productos (codigo_barras, nombre, categoria, marca, precio, stock) VALUES
('7750001', 'Comida Perro Adulto 15kg', 'Alimento', 'Ricocan', 120.00, 20),
('7750002', 'Arena para Gato 5kg', 'Higiene', 'ArenaCat', 25.00, 50),
('7750003', 'Juguete Hueso de Goma', 'Juguetes', 'Kong', 35.00, 30),
('7750004', 'Collar Antipulgas', 'Accesorios', 'Seresto', 80.00, 15),
('7750005', 'Cama Acolchada M', 'Accesorios', 'PetSleep', 60.00, 10),
('7750006', 'Plato de Acero', 'Accesorios', 'Durapet', 15.00, 40),
('7750007', 'Cepillo Doble Cara', 'Higiene', 'Furminator', 40.00, 25),
('7750008', 'Galletas Premios', 'Snacks', 'DogChow', 10.00, 100),
('7750009', 'Transportador Gato', 'Accesorios', 'CatTravel', 90.00, 8),
('7750010', 'Rascador Torre', 'Juguetes', 'CatFun', 150.00, 5);

-- =================================================================================
-- 9. TABLA HISTORIAS CLINICAS (Relacionado 1 a 1 con Citas)
-- =================================================================================
INSERT INTO historias_clinicas (fecha_registro, observaciones, peso, temperatura, cita_id) VALUES
('2023-11-01 10:15:00', 'Paciente presenta leve cojera. Se recomienda reposo.', 25.5, 38.5, 1),
('2023-11-02 11:45:00', 'Chequeo general ok. Vacunación pendiente.', 4.2, 38.0, 2),
('2023-11-03 09:15:00', 'Limpieza dental realizada sin complicaciones.', 18.0, 38.2, 3),
('2023-11-04 15:15:00', 'Corte de pelo y baño medicado.', 28.0, 38.1, 4),
('2023-11-05 16:15:00', 'Presenta otitis leve en oído izquierdo.', 32.0, 39.0, 5),
('2023-11-06 10:45:00', 'Control post operatorio. Herida sanando bien.', 3.5, 38.4, 6),
('2023-11-07 12:15:00', 'Cachorro en buen estado. Se aplica desparasitante.', 5.0, 38.6, 7),
('2023-11-08 14:15:00', 'Gato con estrés, se recomienda feromonas.', 4.8, 38.3, 8),
('2023-11-09 11:15:00', 'Alergia alimentaria detectada. Cambio de dieta.', 8.0, 38.2, 9),
('2023-11-10 09:45:00', 'Chequeo rutinario. Todo en orden.', 2.5, 37.9, 10);

-- =================================================================================
-- 10. TABLA CARNET VACUNACION
-- =================================================================================
INSERT INTO carnet_vacunacion (fecha_aplicacion, fecha_proxima, mascota_id, vacuna_id, veterinario_id) VALUES
('2023-11-01', '2024-11-01', 1, 1, 1),
('2023-11-02', '2024-11-02', 2, 2, 2),
('2023-11-03', '2024-11-03', 3, 3, 3),
('2023-11-04', '2024-05-04', 4, 4, 4),
('2023-11-05', '2024-11-05', 5, 5, 5),
('2023-11-06', '2024-11-06', 6, 6, 6),
('2023-11-07', '2024-02-07', 7, 10, 7),
('2023-11-08', '2024-11-08', 8, 2, 8),
('2023-11-09', '2024-11-09', 9, 3, 9),
('2023-11-10', '2024-11-10', 10, 1, 10);

-- =================================================================================
-- 11. TABLA RECETAS MEDICAS
-- =================================================================================
INSERT INTO recetas_medicas (dosis, frecuencia, duracion, cita_id, medicamento_id) VALUES
('1 tableta', 'Cada 12 horas', '5 días', 1, 2),
('2 ml', 'Cada 24 horas', '3 días', 2, 6),
('1 pastilla', 'Dosis única', '1 día', 3, 3),
('Media tableta', 'Cada 8 horas', '7 días', 4, 1),
('3 gotas', 'Cada 12 horas', '10 días', 5, 7),
('1 pipeta', 'Mensual', '3 meses', 6, 4),
('1 tableta', 'Cada 24 horas', '15 días', 7, 10),
('2 gotas', 'Cada 8 horas', '5 días', 8, 8),
('Baño', 'Semanal', '4 semanas', 9, 9),
('1 ml', 'Cada 12 horas', '3 días', 10, 5);

-- =================================================================================
-- 12. TABLA COMPROBANTES DE PAGO
-- Nota: Los totales coinciden con los precios de la tabla CITAS
-- =================================================================================
INSERT INTO comprobantes_pago (fecha_emision, serie, numero, total, metodo_pago, cita_id) VALUES
('2023-11-01 10:30:00', 'F001', '00000001', 40.00, 'EFECTIVO', 1),
('2023-11-02 12:00:00', 'B001', '00000002', 55.00, 'YAPE', 2),
('2023-11-03 09:45:00', 'F001', '00000003', 70.00, 'TARJETA', 3),
('2023-11-04 16:00:00', 'B001', '00000004', 90.00, 'EFECTIVO', 4),
('2023-11-05 17:00:00', 'F001', '00000005', 110.00, 'PLIN', 5),
('2023-11-06 11:30:00', 'B001', '00000006', 130.00, 'TARJETA', 6),
('2023-11-07 12:30:00', 'B001', '00000007', 40.00, 'EFECTIVO', 7),
('2023-11-08 14:45:00', 'F001', '00000008', 55.00, 'YAPE', 8),
('2023-11-09 11:45:00', 'F001', '00000009', 90.00, 'TARJETA', 9),
('2023-11-10 10:00:00', 'B001', '00000010', 40.00, 'EFECTIVO', 10);