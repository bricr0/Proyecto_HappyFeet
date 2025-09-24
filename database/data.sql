-- ============================================
--   CLÍNICA VETERINARIA - BASE DE DATOS
-- ============================================
CREATE DATABASE HappyFeetDB;
USE `HappyFeetDB`;

-- ------------------------------
-- Catálogos
-- ------------------------------
CREATE TABLE especies (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE razas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  especie_id INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  FOREIGN KEY (especie_id) REFERENCES especies(id) ON DELETE CASCADE
);

CREATE TABLE cita_estados (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre ENUM('Programada','Finalizada','Cancelada','En Proceso','Reprogramada') NOT NULL
);

-- ------------------------------
-- Dueños y Mascotas
-- ------------------------------
CREATE TABLE duenos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre_completo VARCHAR(255) NOT NULL,
  documento_identidad VARCHAR(50) UNIQUE,
  direccion VARCHAR(500),
  telefono VARCHAR(50),
  correo_electronico VARCHAR(150) UNIQUE,
  contacto_emergencia VARCHAR(255)
);

CREATE TABLE mascotas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dueno_id INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  raza_id INT,
  fecha_nacimiento DATE,
  sexo ENUM('Macho','Hembra') DEFAULT 'Macho',
  microchip VARCHAR(100) UNIQUE,
  foto_url VARCHAR(500),
  alergias TEXT,
  condiciones_preexistentes TEXT,
  peso_kg DECIMAL(5,2),
  notas_medicas TEXT,
  FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE RESTRICT,
  FOREIGN KEY (raza_id) REFERENCES razas(id) ON DELETE SET NULL
);

-- Historial de propietarios (transferencias)
CREATE TABLE mascota_propietarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  mascota_id INT NOT NULL,
  dueno_id INT NOT NULL,
  fecha_inicio DATETIME DEFAULT CURRENT_TIMESTAMP,
  fecha_fin DATETIME,
  FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE,
  FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE CASCADE
);

-- ------------------------------
-- Veterinarios y Proveedores
-- ------------------------------
CREATE TABLE veterinarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre_completo VARCHAR(255) NOT NULL,
  telefono VARCHAR(50),
  email VARCHAR(150)
);

CREATE TABLE proveedores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  contacto VARCHAR(255),
  telefono VARCHAR(50),
  email VARCHAR(150)
);

-- ------------------------------
-- Inventario
-- ------------------------------
CREATE TABLE inventario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre_producto VARCHAR(255) NOT NULL,
  tipo ENUM('medicamento','vacuna','material','alimento','accesorio') DEFAULT 'medicamento',
  fabricante VARCHAR(255),
  cantidad_stock INT DEFAULT 0,
  stock_minimo INT DEFAULT 0,
  fecha_vencimiento DATE,
  precio_venta DECIMAL(10,2) DEFAULT 0.00,
  proveedor_id INT,
  lote VARCHAR(100),
  notas TEXT,
  FOREIGN KEY (proveedor_id) REFERENCES proveedores(id) ON DELETE SET NULL
);

-- Alertas de inventario
CREATE TABLE alertas_inventario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  inventario_id INT,
  tipo_alerta ENUM('stock_bajo','producto_vencido','stock_agotado','reorden_necesario') DEFAULT 'stock_bajo',
  mensaje TEXT,
  fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
  leido BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (inventario_id) REFERENCES inventario(id) ON DELETE SET NULL
);

-- ------------------------------
-- Citas y Consultas
-- ------------------------------
CREATE TABLE citas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  mascota_id INT NOT NULL,
  fecha_hora DATETIME NOT NULL,
  estado_id INT NOT NULL,
  veterinario_id INT,
  motivo ENUM('consulta_general','vacunacion','cirugia','urgencia','control','estetica') DEFAULT 'consulta_general',
  observaciones TEXT,
  FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE,
  FOREIGN KEY (estado_id) REFERENCES cita_estados(id) ON DELETE RESTRICT,
  FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id) ON DELETE SET NULL,
  UNIQUE KEY ux_mascota_fecha (mascota_id, fecha_hora)
);

CREATE TABLE consultas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cita_id INT NOT NULL,
  veterinario_id INT,
  diagnostico TEXT,
  tratamiento_recomendado TEXT,
  procedimientos TEXT,
  fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (cita_id) REFERENCES citas(id) ON DELETE CASCADE,
  FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id) ON DELETE SET NULL
);

-- ------------------------------
-- Facturación
-- ------------------------------
CREATE TABLE facturas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dueno_id INT NOT NULL,
  fecha_emision DATETIME DEFAULT CURRENT_TIMESTAMP,
  subtotal DECIMAL(12,2) DEFAULT 0,
  impuestos DECIMAL(12,2) DEFAULT 0,
  total DECIMAL(12,2) DEFAULT 0,
  estado ENUM('pendiente','pagada','cancelada','vencida') DEFAULT 'pendiente',
  FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE RESTRICT
);

CREATE TABLE elementos_factura (
  id INT AUTO_INCREMENT PRIMARY KEY,
  factura_id INT NOT NULL,
  producto_id INT NULL,
  descripcion VARCHAR(500) NOT NULL,
  cantidad INT NOT NULL DEFAULT 1,
  precio_unitario DECIMAL(12,2) DEFAULT 0,
  subtotal DECIMAL(12,2) GENERATED ALWAYS AS (cantidad * precio_unitario) VIRTUAL,
  tipo ENUM('producto','servicio','consulta') DEFAULT 'producto',
  consulta_id INT NULL,
  FOREIGN KEY (factura_id) REFERENCES facturas(id) ON DELETE CASCADE,
  FOREIGN KEY (producto_id) REFERENCES inventario(id) ON DELETE SET NULL,
  FOREIGN KEY (consulta_id) REFERENCES consultas(id) ON DELETE SET NULL
);

-- ------------------------------
-- Actividades Especiales
-- ------------------------------
-- Contratos y adopciones
CREATE TABLE contratos_adopcion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  mascota_id INT NOT NULL,
  adoptante_id INT NOT NULL,
  fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
  contenido TEXT,
  firmado BOOLEAN DEFAULT FALSE,
  firmado_por VARCHAR(255),
  firma_fecha DATETIME,
  tipo_contrato ENUM('adopcion_definitiva','adopcion_temporal','acogida') DEFAULT 'adopcion_definitiva',
  FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE,
  FOREIGN KEY (adoptante_id) REFERENCES duenos(id) ON DELETE CASCADE
);

CREATE TABLE adopciones (
  id INT AUTO_INCREMENT PRIMARY KEY,
  mascota_id INT NOT NULL,
  adoptante_id INT NOT NULL,
  fecha_adopcion DATETIME DEFAULT CURRENT_TIMESTAMP,
  tipo ENUM('adopcion','temporal') DEFAULT 'adopcion',
  estado ENUM('pendiente','completada','rechazada','cancelada') DEFAULT 'pendiente',
  contrato_id INT,
  notas TEXT,
  FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE,
  FOREIGN KEY (adoptante_id) REFERENCES duenos(id) ON DELETE CASCADE,
  FOREIGN KEY (contrato_id) REFERENCES contratos_adopcion(id) ON DELETE SET NULL
);

-- Jornadas de vacunación
CREATE TABLE jornadas_vacunacion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255),
  fecha DATE NOT NULL,
  ubicacion VARCHAR(255),
  notas TEXT,
  creado_por VARCHAR(255),
  estado ENUM('programada','en_curso','finalizada','cancelada') DEFAULT 'programada',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE jornada_asistencias (
  id INT AUTO_INCREMENT PRIMARY KEY,
  jornada_id INT NOT NULL,
  mascota_id INT NOT NULL,
  dueno_id INT NOT NULL,
  vacunado BOOLEAN DEFAULT FALSE,
  producto_id INT NULL,
  lote VARCHAR(100),
  cantidad INT DEFAULT 1,
  fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
  estado ENUM('programada','asistio','no_asistio','cancelada') DEFAULT 'programada',
  observaciones TEXT,
  FOREIGN KEY (jornada_id) REFERENCES jornadas_vacunacion(id) ON DELETE CASCADE,
  FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE,
  FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE CASCADE,
  FOREIGN KEY (producto_id) REFERENCES inventario(id) ON DELETE SET NULL
);

-- Club de fidelidad
CREATE TABLE club_fidelidad (
  dueno_id INT PRIMARY KEY,
  puntos_balance INT DEFAULT 0,
  nivel ENUM('bronce','plata','oro','platino') DEFAULT 'bronce',
  actualizado DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE CASCADE
);

CREATE TABLE puntos_movimientos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dueno_id INT NOT NULL,
  puntos INT NOT NULL,
  tipo ENUM('acumulacion','redencion','bonificacion','ajuste') DEFAULT 'acumulacion',
  descripcion VARCHAR(500),
  fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE CASCADE
);

CREATE TABLE reglas_puntos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  unidades_por_punto INT DEFAULT 1000,
  activo BOOLEAN DEFAULT TRUE
);

INSERT IGNORE INTO reglas_puntos (id, nombre, unidades_por_punto, activo)
VALUES (1, 'Regla por defecto', 1000, TRUE);

CREATE TABLE redenciones_puntos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dueno_id INT NOT NULL,
  puntos_usados INT NOT NULL,
  descripcion VARCHAR(500),
  estado ENUM('pendiente','aplicada','cancelada') DEFAULT 'pendiente',
  fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE CASCADE
);

-- ------------------------------
-- Procedimientos
-- ------------------------------
DELIMITER $$

-- Transferencia de propiedad
CREATE PROCEDURE transferir_propiedad(IN p_mascota_id INT, IN p_nuevo_dueno_id INT)
BEGIN
  UPDATE mascota_propietarios
  SET fecha_fin = NOW()
  WHERE mascota_id = p_mascota_id AND fecha_fin IS NULL;

  INSERT INTO mascota_propietarios (mascota_id, dueno_id, fecha_inicio)
  VALUES (p_mascota_id, p_nuevo_dueno_id, NOW());

  UPDATE mascotas SET dueno_id = p_nuevo_dueno_id WHERE id = p_mascota_id;
END$$

-- Crear contrato de adopción
CREATE PROCEDURE crear_contrato_adopcion(
  IN p_mascota_id INT,
  IN p_adoptante_id INT,
  IN p_firmante_nombre VARCHAR(255),
  IN p_tipo_contrato ENUM('adopcion_definitiva','adopcion_temporal','acogida'),
  OUT p_contrato_id INT
)
BEGIN
  DECLARE v_mascota_nombre VARCHAR(255);
  DECLARE v_propietario_actual VARCHAR(255);
  DECLARE v_adoptante_nombre VARCHAR(255);

  SELECT nombre INTO v_mascota_nombre FROM mascotas WHERE id = p_mascota_id;
  SELECT dueno_id INTO @v_dueno_actual FROM mascotas WHERE id = p_mascota_id;
  SELECT nombre_completo INTO v_propietario_actual FROM duenos WHERE id = @v_dueno_actual;
  SELECT nombre_completo INTO v_adoptante_nombre FROM duenos WHERE id = p_adoptante_id;

  SET @contenido = CONCAT(
    'CONTRATO DE ', UPPER(p_tipo_contrato), '\n\n',
    'Mascota: ', IFNULL(v_mascota_nombre,'--'), '\n',
    'Propietario previo: ', IFNULL(v_propietario_actual,'--'), '\n',
    'Adoptante: ', IFNULL(v_adoptante_nombre,'--'), '\n',
    'Tipo: ', p_tipo_contrato, '\n',
    'Fecha: ', DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), '\n\n',
    'Terminos: El adoptante declara recibir la mascota...'
  );

  INSERT INTO contratos_adopcion (mascota_id, adoptante_id, fecha, contenido, firmado, firmado_por, tipo_contrato)
  VALUES (p_mascota_id, p_adoptante_id, NOW(), @contenido, FALSE, p_firmante_nombre, p_tipo_contrato);

  SET p_contrato_id = LAST_INSERT_ID();
END$$

-- Registrar adopción
CREATE PROCEDURE registrar_adopcion(
  IN p_mascota_id INT,
  IN p_nuevo_dueno_id INT,
  IN p_firmante_nombre VARCHAR(255),
  IN p_tipo_adopcion ENUM('adopcion','temporal'),
  OUT p_adopcion_id INT
)
BEGIN
  DECLARE v_contrato_id INT;
  DECLARE v_tipo_contrato ENUM('adopcion_definitiva','adopcion_temporal','acogida');

  SET v_tipo_contrato = CASE
    WHEN p_tipo_adopcion = 'adopcion' THEN 'adopcion_definitiva'
    ELSE 'adopcion_temporal'
  END;

  CALL crear_contrato_adopcion(p_mascota_id, p_nuevo_dueno_id, p_firmante_nombre, v_tipo_contrato, v_contrato_id);

  INSERT INTO adopciones (mascota_id, adoptante_id, fecha_adopcion, tipo, estado, contrato_id)
  VALUES (p_mascota_id, p_nuevo_dueno_id, NOW(), p_tipo_adopcion, 'completada', v_contrato_id);

  SET p_adopcion_id = LAST_INSERT_ID();
  CALL transferir_propiedad(p_mascota_id, p_nuevo_dueno_id);
END$$

-- Registrar asistencia de vacunación
CREATE PROCEDURE registrar_asistencia_vacunacion(
  IN p_jornada_id INT,
  IN p_mascota_id INT,
  IN p_producto_id INT,
  IN p_cantidad INT,
  IN p_lote VARCHAR(100),
  IN p_dueno_id INT,
  OUT p_asistencia_id INT
)
BEGIN
  DECLARE v_stock INT;
  DECLARE v_venc DATE;

  START TRANSACTION;
  SELECT cantidad_stock, fecha_vencimiento INTO v_stock, v_venc
  FROM inventario WHERE id = p_producto_id FOR UPDATE;

  IF v_stock < p_cantidad THEN
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Stock insuficiente';
  END IF;

  IF v_venc IS NOT NULL AND v_venc < CURDATE() THEN
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Producto vencido';
  END IF;

  UPDATE inventario SET cantidad_stock = cantidad_stock - p_cantidad WHERE id = p_producto_id;

  INSERT INTO jornada_asistencias (jornada_id, mascota_id, dueno_id, vacunado, producto_id, lote, cantidad, fecha_registro, estado)
  VALUES (p_jornada_id, p_mascota_id, p_dueno_id, TRUE, p_producto_id, p_lote, p_cantidad, NOW(), 'asistio');

  SET p_asistencia_id = LAST_INSERT_ID();
  COMMIT;
END$$

-- Canjear puntos
CREATE PROCEDURE canjear_puntos(
  IN p_dueno_id INT,
  IN p_puntos INT,
  IN p_descripcion VARCHAR(500),
  OUT p_ok BOOLEAN
)
BEGIN
  DECLARE v_saldo INT DEFAULT 0;
  SELECT puntos_balance INTO v_saldo FROM club_fidelidad WHERE dueno_id = p_dueno_id;

  IF v_saldo IS NULL OR v_saldo < p_puntos THEN
    SET p_ok = FALSE;
  ELSE
    INSERT INTO redenciones_puntos (dueno_id, puntos_usados, descripcion, estado)
    VALUES (p_dueno_id, p_puntos, p_descripcion, 'aplicada');

    INSERT INTO puntos_movimientos (dueno_id, puntos, tipo, descripcion)
    VALUES (p_dueno_id, p_puntos, 'redencion', p_descripcion);

    UPDATE club_fidelidad SET puntos_balance = puntos_balance - p_puntos, actualizado = NOW()
    WHERE dueno_id = p_dueno_id;
    SET p_ok = TRUE;
  END IF;
END$$

DELIMITER ;

-- ------------------------------
-- Triggers
-- ------------------------------
DELIMITER $$

-- Deducción de stock y validación

CREATE TRIGGER trg_elemento_factura_before_insert
BEFORE INSERT ON elementos_factura
FOR EACH ROW
BEGIN
  -- Declaraciones SIEMPRE primero
  DECLARE v_stock INT;
  DECLARE v_venc DATE;

  IF NEW.producto_id IS NOT NULL THEN
    SELECT cantidad_stock, fecha_vencimiento
    INTO v_stock, v_venc
    FROM inventario
    WHERE id = NEW.producto_id
    FOR UPDATE;

    IF v_stock < NEW.cantidad THEN
      SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Stock insuficiente';
    END IF;

    IF v_venc IS NOT NULL AND v_venc < CURDATE() THEN
      SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Producto vencido';
    END IF;

    UPDATE inventario
    SET cantidad_stock = cantidad_stock - NEW.cantidad
    WHERE id = NEW.producto_id;

    IF (SELECT cantidad_stock FROM inventario WHERE id = NEW.producto_id)
       <= (SELECT stock_minimo FROM inventario WHERE id = NEW.producto_id) THEN
      INSERT INTO alertas_inventario (inventario_id, tipo_alerta, mensaje)
      VALUES (NEW.producto_id, 'stock_bajo', CONCAT('Stock bajo producto ID=', NEW.producto_id));
    END IF;
  END IF;
END$$

-- Acumulación de puntos
CREATE TRIGGER trg_facturas_after_insert_puntos
AFTER INSERT ON facturas
FOR EACH ROW
BEGIN
  DECLARE v_unidades_por_punto INT DEFAULT 1000;
  DECLARE v_puntos INT DEFAULT 0;

  SELECT unidades_por_punto INTO v_unidades_por_punto
  FROM reglas_puntos WHERE activo = TRUE LIMIT 1;

  SET v_puntos = FLOOR(NEW.total / v_unidades_por_punto);

  IF v_puntos > 0 THEN
    INSERT INTO puntos_movimientos (dueno_id, puntos, tipo, descripcion)
    VALUES (NEW.dueno_id, v_puntos, 'acumulacion', CONCAT('Acumulo factura ID ', NEW.id));

    INSERT INTO club_fidelidad (dueno_id, puntos_balance, actualizado)
    VALUES (NEW.dueno_id, v_puntos, NOW())
    ON DUPLICATE KEY UPDATE puntos_balance = puntos_balance + v_puntos, actualizado = NOW();
  END IF;
END$$

-- Actualizar nivel de fidelidad
CREATE TRIGGER trg_club_fidelidad_after_update
AFTER UPDATE ON club_fidelidad
FOR EACH ROW
BEGIN
  DECLARE v_nuevo_nivel ENUM('bronce','plata','oro','platino');

  IF NEW.puntos_balance >= 5000 THEN
    SET v_nuevo_nivel = 'platino';
  ELSEIF NEW.puntos_balance >= 2000 THEN
    SET v_nuevo_nivel = 'oro';
  ELSEIF NEW.puntos_balance >= 500 THEN
    SET v_nuevo_nivel = 'plata';
  ELSE
    SET v_nuevo_nivel = 'bronce';
  END IF;

  IF OLD.nivel != v_nuevo_nivel THEN
    UPDATE club_fidelidad SET nivel = v_nuevo_nivel WHERE dueno_id = NEW.dueno_id;
  END IF;
END$$

DELIMITER ;

-- ------------------------------
-- Datos iniciales
-- ------------------------------
INSERT IGNORE INTO cita_estados (id, nombre) VALUES
(1,'Programada'), (2,'Finalizada'), (3,'Cancelada'), (4,'En Proceso'), (5,'Reprogramada');

ALTER TABLE duenos
ADD COLUMN estado ENUM('activo', 'inactivo') NOT NULL DEFAULT 'activo';
