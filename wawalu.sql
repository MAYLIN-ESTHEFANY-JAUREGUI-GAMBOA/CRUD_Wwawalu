-- =========================================
-- CREAR BASE DE DATOS
-- =========================================

CREATE DATABASE IF NOT EXISTS Wwawalu
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE Wwawalu;

-- =========================================
-- TABLA: categorias
-- =========================================

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- =========================================
-- TABLA: productos
-- =========================================

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(255),
    id_categoria INT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,

    CONSTRAINT fk_producto_categoria
    FOREIGN KEY (id_categoria)
    REFERENCES categorias(id_categoria)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

-- =========================================
-- INSERTAR CATEGORÍAS
-- =========================================

INSERT INTO categorias (nombre, descripcion) VALUES
('Bebidas', 'Productos líquidos y refrescos'),
('Snacks', 'Aperitivos y bocadillos'),
('Lácteos', 'Productos derivados de la leche'),
('Panadería', 'Panes y productos horneados'),
('Limpieza', 'Productos de limpieza del hogar');

-- =========================================
-- INSERTAR PRODUCTOS
-- =========================================

INSERT INTO productos
(codigo, nombre, descripcion, id_categoria, precio, stock)
VALUES

('B001', 'Coca Cola 500ml', 'Gaseosa Coca Cola', 1, 3.50, 100),

('B002', 'Agua San Luis', 'Agua mineral sin gas', 1, 2.00, 80),

('S001', 'Papas Lays', 'Papas fritas clásicas', 2, 4.50, 60),

('S002', 'Doritos Nacho', 'Snack sabor queso', 2, 5.00, 50),

('L001', 'Leche Gloria', 'Leche evaporada', 3, 4.20, 70),

('L002', 'Yogurt Fresa', 'Yogurt sabor fresa', 3, 6.80, 40),

('P001', 'Pan Francés', 'Pan tradicional fresco', 4, 0.50, 200),

('P002', 'Queque Vainilla', 'Queque artesanal', 4, 12.00, 15),

('LIM001', 'Lejía Sapolio', 'Lejía desinfectante', 5, 7.50, 35),

('LIM002', 'Detergente Ariel', 'Detergente en polvo', 5, 18.90, 25);

-- =========================================
-- CONSULTA DE PRUEBA
-- =========================================

SELECT 
    p.id_producto,
    p.codigo,
    p.nombre,
    p.descripcion,
    c.nombre AS categoria,
    p.precio,
    p.stock
FROM productos p
LEFT JOIN categorias c
ON p.id_categoria = c.id_categoria;