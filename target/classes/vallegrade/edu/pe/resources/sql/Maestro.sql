-- =========================================================
-- BD: Wwawalu
-- =========================================================
DROP DATABASE IF EXISTS Wwawalu;
CREATE DATABASE Wwawalu CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE Wwawalu;

-- ------------------------------
-- Tabla: categorias
-- ------------------------------
CREATE TABLE categorias (
  id_categoria   INT AUTO_INCREMENT PRIMARY KEY,
  nombre         VARCHAR(80) NOT NULL UNIQUE
) ENGINE=InnoDB;

INSERT INTO categorias (nombre) VALUES
('Material didáctico'),
('Juego educativo'),
('Lectura'),
('Vestimenta'),
('Higiene');

-- ------------------------------
-- Tabla: productos
-- ------------------------------
CREATE TABLE productos (
  id_producto    INT AUTO_INCREMENT PRIMARY KEY,
  codigo         VARCHAR(10)  NOT NULL UNIQUE,         
  nombre         VARCHAR(120) NOT NULL,
  descripcion    VARCHAR(255) NOT NULL,
  id_categoria   INT NOT NULL,
  precio         DECIMAL(10,2) NOT NULL,
  stock          INT NOT NULL DEFAULT 0,
  CONSTRAINT fk_prod_cat FOREIGN KEY (id_categoria)
    REFERENCES categorias(id_categoria)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ------------------------------
-- Datos de ejemplo (igual que en tu imagen)
-- ------------------------------
INSERT INTO productos (codigo, nombre, descripcion, id_categoria, precio, stock)
VALUES
('PROD001','Plastilina','Masa moldeable no tóxica para niños',
  (SELECT id_categoria FROM categorias WHERE nombre='Material didáctico'), 6.00, 25),

('PROD002','Colores','Caja de 12 crayolas lavables',
  (SELECT id_categoria FROM categorias WHERE nombre='Material didáctico'), 8.50, 30),

('PROD003','Rompecabezas','Rompecabezas de figuras educativas',
  (SELECT id_categoria FROM categorias WHERE nombre='Juego educativo'), 12.00, 15),

('PROD004','Libros','Cuentos infantiles ilustrados',
  (SELECT id_categoria FROM categorias WHERE nombre='Lectura'), 10.00, 20),

('PROD005','Sombreros','Sombrero de tela para protección solar',
  (SELECT id_categoria FROM categorias WHERE nombre='Vestimenta'), 15.00, 18),

('PROD006','Buzos','Buzo infantil (tallas 2–6 años)',
  (SELECT id_categoria FROM categorias WHERE nombre='Vestimenta'), 25.00, 12),

('PROD007','Medias','Par de medias de algodón',
  (SELECT id_categoria FROM categorias WHERE nombre='Vestimenta'), 4.50, 40),

('PROD008','Jabón','Jabón neutro para niños',
  (SELECT id_categoria FROM categorias WHERE nombre='Higiene'), 4.50, 35),

('PROD009','Shampoo para bebés','Shampoo hipoalergénico',
  (SELECT id_categoria FROM categorias WHERE nombre='Higiene'), 8.00, 22),

('PROD010','Cepillos de dientes','Cepillo dental infantil',
  (SELECT id_categoria FROM categorias WHERE nombre='Higiene'), 3.50, 28);

-- ------------------------------
-- Vista para mostrar listado
-- ------------------------------
CREATE OR REPLACE VIEW vw_listado_productos AS
SELECT
  p.codigo          AS CODIGO,
  p.nombre          AS NOMBRE,
  p.descripcion     AS DESCRIPCION,
  c.nombre          AS CATEGORIA,
  CONCAT('S/ ', FORMAT(p.precio, 2, 'es_PE')) AS PRECIO,
  p.stock           AS STOCK
FROM productos p
JOIN categorias c ON c.id_categoria = p.id_categoria
ORDER BY p.codigo;

-- Para probar:
SELECT * FROM wwawalu.productos;
