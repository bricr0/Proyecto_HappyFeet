-- ============================================
-- INSERTS PARA CLÍNICA VETERINARIA
-- ============================================

-- ------------------------------
-- Especies
-- ------------------------------
INSERT INTO especies (nombre) VALUES
('Perro'),
('Gato'),
('Conejo'),
('Ave'),
('Hámster');

-- ------------------------------
-- Razas
-- ------------------------------
INSERT INTO razas (especie_id, nombre) VALUES
(1, 'Labrador Retriever'),
(1, 'Pastor Alemán'),
(2, 'Siamés'),
(2, 'Persa'),
(3, 'Holandés Enano');

-- ------------------------------
-- Estados de Cita
-- ------------------------------
INSERT INTO cita_estados (nombre) VALUES
('Programada'),
('Finalizada'),
('Cancelada'),
('En Proceso'),
('Reprogramada');

-- ------------------------------
-- Dueños
-- ------------------------------
INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, correo_electronico, contacto_emergencia) VALUES
('María González Pérez', '12345678A', 'Calle Principal 123, Ciudad', '600123456', 'maria.gonzalez@email.com', 'Juan González - 600111222'),
('Carlos López Martínez', '87654321B', 'Avenida Central 456, Pueblo', '600234567', 'carlos.lopez@email.com', 'Ana Martínez - 600222333'),
('Ana Rodríguez Silva', '11223344C', 'Plaza Mayor 789, Villa', '600345678', 'ana.rodriguez@email.com', 'Pedro Silva - 600333444'),
('David Fernández Ruiz', '44332211D', 'Calle Secundaria 321, Metrópolis', '600456789', 'david.fernandez@email.com', 'Laura Ruiz - 600444555'),
('Laura García Díaz', '55667788E', 'Avenida Norte 654, Capital', '600567890', 'laura.garcia@email.com', 'Miguel Díaz - 600555666');

-- ------------------------------
-- Mascotas
-- ------------------------------
INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, microchip, alergias, condiciones_preexistentes, peso_kg) VALUES
(1, 'Max', 1, '2020-03-15', 'Macho', 'CHIP001', 'Ninguna conocida', 'Vacunación completa', 25.5),
(2, 'Luna', 2, '2019-07-20', 'Hembra', 'CHIP002', 'Polen', 'Problemas dermatológicos', 30.2),
(3, 'Simba', 3, '2021-01-10', 'Macho', 'CHIP003', 'Ninguna', 'Esterilizado', 4.8),
(4, 'Nala', 4, '2020-11-05', 'Hembra', 'CHIP004', 'Ciertos alimentos', 'Asma leve', 5.2),
(5, 'Rocky', 1, '2022-05-30', 'Macho', 'CHIP005', 'Ninguna', 'Vacunación pendiente', 18.7);


-- Inserto mascota
INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_kg)
VALUES (1, 'Firulais', 3, '2020-05-01', 'Macho', 15.5);

-- Recupero el ID generado (supongamos que fue 6)
SELECT LAST_INSERT_ID();

-- Inserto historial propietario
INSERT INTO mascota_propietarios (mascota_id, dueno_id, fecha_inicio, fecha_fin)
VALUES (6, 1, NOW(), NULL);
-- ------------------------------
-- Historial de Propietarios
-- ------------------------------

-- ------------------------------
-- Veterinarios
-- ------------------------------
INSERT INTO veterinarios (nombre_completo, telefono, email) VALUES
('Dra. Elena Vargas Morales', '611223344', 'elena.vargas@clinica.com'),
('Dr. Javier Mendoza Ruiz', '622334455', 'javier.mendoza@clinica.com'),
('Dra. Sofia Castro Jiménez', '633445566', 'sofia.castro@clinica.com'),
('Dr. Roberto Navarro Díaz', '644556677', 'roberto.navarro@clinica.com'),
('Dra. Carmen Reyes Ortega', '655667788', 'carmen.reyes@clinica.com');

-- ------------------------------
-- Proveedores
-- ------------------------------
INSERT INTO proveedores (nombre, contacto, telefono, email) VALUES
('VetSupply S.A.', 'Juan Pérez', '900111222', 'ventas@vetsupply.com'),
('AnimalHealth Corp', 'María López', '900222333', 'pedidos@animalhealth.com'),
('PetCare Solutions', 'Carlos García', '900333444', 'info@petcaresolutions.com'),
('BioVet Internacional', 'Ana Martínez', '900444555', 'contacto@biovet.com'),
('FarmVet Distribuciones', 'David Sánchez', '900555666', 'cliente@farmvet.com');

-- ------------------------------
-- Inventario
-- ------------------------------
INSERT INTO inventario (nombre_producto, tipo, fabricante, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id, lote) VALUES
('Vacuna Triple Felina', 'vacuna', 'Zoetis', 50, 10, '2024-12-31', 45.00, 1, 'LOTE-VF2023'),
('Antiparasitario Interno', 'medicamento', 'Bayer', 30, 5, '2025-06-30', 25.50, 2, 'LOTE-API2024'),
('Pienso Premium Perro Adulto', 'alimento', 'Royal Canin', 20, 3, '2024-09-15', 60.00, 3, 'LOTE-PPP2024'),
('Jeringas Estériles 5ml', 'material', 'BD', 100, 20, '2026-03-01', 0.80, 4, 'LOTE-JE2025'),
('Collar Antipulgas', 'accesorio', 'Seresto', 15, 5, '2025-12-31', 35.00, 5, 'LOTE-CA2024');

-- ------------------------------
-- Alertas de Inventario
-- ------------------------------
INSERT INTO alertas_inventario (inventario_id, tipo_alerta, mensaje, leido) VALUES
(2, 'stock_bajo', 'Stock de antiparasitario interno por debajo del mínimo', FALSE),
(3, 'reorden_necesario', 'Pienso premium necesita reorden pronto', FALSE),
(1, 'stock_bajo', 'Vacunas felinas en stock bajo', TRUE),
(5, 'stock_agotado', 'Collares antipulgas agotados', FALSE),
(4, 'producto_vencido', 'Jeringas próximas a vencer', FALSE);

-- ------------------------------
-- Citas
-- ------------------------------
INSERT INTO citas (mascota_id, fecha_hora, estado_id, veterinario_id, motivo, observaciones) VALUES
(6, '2024-01-15 10:00:00', 1, 1, 'vacunacion', 'Primera vacuna anual'),
(2, '2024-01-16 11:30:00', 2, 2, 'consulta_general', 'Control dermatológico'),
(3, '2024-01-17 09:00:00', 4, 3, 'control', 'Revisión post-operatoria'),
(4, '2024-01-18 16:00:00', 1, 4, 'urgencia', 'Problemas respiratorios'),
(5, '2024-01-19 12:30:00', 3, 5, 'estetica', 'Baño y corte de pelo');

SELECT * FROM mascotas;
SELECT * FROM citas c;
-- ------------------------------
-- Consultas
-- ------------------------------
INSERT INTO consultas (cita_id, veterinario_id, diagnostico, tratamiento_recomendado) VALUES
(1, 1, 'Animal sano, peso adecuado', 'Vacuna aplicada, próximo control en 1 año'),
(2, 2, 'Dermatitis alérgica', 'Antihistamínico y champú especial'),
(3, 3, 'Recuperación satisfactoria', 'Continuar con medicación por 5 días'),
(4, 4, 'Crisis asmática leve', 'Broncodilatador y reposo'),
(5, 5, 'Cancelada por cliente', 'Reprogramar para próxima semana');

-- ------------------------------
-- Facturas
-- ------------------------------
INSERT INTO facturas (dueno_id, subtotal, impuestos, total, estado) VALUES
(1, 45.00, 9.45, 54.45, 'pagada'),
(2, 85.50, 17.96, 103.46, 'pendiente'),
(3, 120.00, 25.20, 145.20, 'pagada'),
(4, 60.00, 12.60, 72.60, 'vencida'),
(5, 35.00, 7.35, 42.35, 'cancelada');

-- ------------------------------
-- Elementos de Factura
-- ------------------------------
SELECT * from inventario i;
INSERT INTO elementos_factura (factura_id, producto_id, descripcion, cantidad, precio_unitario, tipo) VALUES
(1, NULL, 'Vacuna Triple Felina', 1, 45.00, 'producto'),
(2, NULL, 'Antiparasitario Interno', 2, 25.50, 'producto'),
(2, NULL, 'Consulta veterinaria', 1, 34.50, 'servicio'),
(3, NULL, 'Pienso Premium 15kg', 2, 60.00, 'producto'),
(4, NULL, 'Urgencia veterinaria', 1, 60.00, 'servicio');



-- ------------------------------
-- Contratos de Adopción
-- ------------------------------
INSERT INTO contratos_adopcion (mascota_id, adoptante_id, contenido, firmado, firmado_por, tipo_contrato) VALUES
(2, 1, 'Contrato de adopción definitiva para Max...', TRUE, 'María González', 'adopcion_definitiva'),
(2, 2, 'Contrato de acogida temporal para Luna...', TRUE, 'Carlos López', 'acogida'),
(3, 3, 'Contrato de adopción para Simba...', FALSE, NULL, 'adopcion_definitiva'),
(4, 4, 'Contrato temporal para Nala...', TRUE, 'David Fernández', 'adopcion_temporal'),
(5, 5, 'Contrato de adopción para Rocky...', TRUE, 'Laura García', 'adopcion_definitiva');

select * from contratos_adopcion ca;

-- ------------------------------
-- Adopciones
-- ------------------------------
INSERT INTO adopciones (mascota_id, adoptante_id, tipo, estado, contrato_id) VALUES
(2, 2, 'adopcion', 'completada', 1),
(2, 2, 'temporal', 'completada', 2),
(3, 3, 'adopcion', 'pendiente', 3),
(4, 4, 'temporal', 'completada', 4),
(5, 5, 'adopcion', 'completada', 5);

-- ------------------------------
-- Jornadas de Vacunación
-- ------------------------------
INSERT INTO jornadas_vacunacion (nombre, fecha, ubicacion, estado, creado_por) VALUES
('Jornada Primavera 2024', '2024-04-15', 'Parque Central', 'programada', 'Dra. Elena Vargas'),
('Campaña Antirrábica', '2024-05-20', 'Plaza de Armas', 'programada', 'Dr. Javier Mendoza'),
('Vacunación Masiva Otoño', '2024-09-10', 'Polideportivo Municipal', 'programada', 'Dra. Sofia Castro'),
('Jornada Comunitaria', '2024-07-05', 'Centro Vecinal Norte', 'finalizada', 'Dr. Roberto Navarro'),
('Campaña Esterilización', '2024-11-15', 'Clínica Central', 'cancelada', 'Dra. Carmen Reyes');

-- ------------------------------
-- Asistencias a Jornadas
-- ------------------------------
INSERT INTO jornada_asistencias (jornada_id, mascota_id, dueno_id, vacunado, producto_id, estado) VALUES
(1, 2, 1, TRUE, 1, 'asistio'),
(1, 2, 2, FALSE, NULL, 'no_asistio'),
(2, 3, 3, TRUE, 1, 'asistio'),
(3, 4, 4, TRUE, 1, 'asistio'),
(4, 5, 5, FALSE, NULL, 'cancelada');

-- ------------------------------
-- Club de Fidelidad
-- ------------------------------
INSERT INTO club_fidelidad (dueno_id, puntos_balance, nivel) VALUES
(1, 1500, 'plata'),
(2, 800, 'bronce'),
(3, 3000, 'oro'),
(4, 500, 'bronce'),
(5, 4500, 'platino');

-- ------------------------------
-- Movimientos de Puntos
-- ------------------------------
INSERT INTO puntos_movimientos (dueno_id, puntos, tipo, descripcion) VALUES
(1, 500, 'acumulacion', 'Compra enero 2024'),
(2, 300, 'acumulacion', 'Consulta veterinaria'),
(3, 1000, 'acumulacion', 'Adopción mascota'),
(4, 200, 'redencion', 'Descuento en producto'),
(5, 1500, 'bonificacion', 'Bonificación aniversario');

-- ------------------------------
-- Reglas de Puntos
-- ------------------------------
INSERT INTO reglas_puntos (nombre, unidades_por_punto, activo) VALUES
('Regla Estándar', 1000, TRUE),
('Promoción Verano', 500, TRUE),
('Clientes Premium', 750, FALSE),
('Productos Alimentos', 800, TRUE),
('Servicios Médicos', 1200, TRUE);

-- ------------------------------
-- Redenciones de Puntos
-- ------------------------------
INSERT INTO redenciones_puntos (dueno_id, puntos_usados, descripcion, estado) VALUES
(1, 200, 'Descuento en consulta', 'aplicada'),
(2, 100, 'Producto antiparasitario', 'pendiente'),
(3, 500, 'Descuento en esterilización', 'aplicada'),
(4, 150, 'Collar antipulgas', 'cancelada'),
(5, 1000, 'Pienso premium gratuito', 'aplicada');
