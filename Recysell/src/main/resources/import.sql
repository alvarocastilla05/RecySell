-- Insertar en user_entity ADMIN
INSERT INTO user_entity (id, username, email, password, enabled, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'admin', 'admin@localhost',
        '{bcrypt}$2y$10$W257cv0HtGxVEbh6wZ/9MuIW/zqMqxUWfC.M0iFL3FBzoCO4ruQZW', true, false);

INSERT INTO user_roles (user_id, roles)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'ADMIN');

-- Insertar en user_entity TRABAJADORES
INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174001', 'jrodriguez', 'jrodriguez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Juan', 'Rodríguez Pérez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174002', 'mlopez', 'mlopez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'María', 'López Fernández', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174003', 'agomez', 'agomez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Alejandro', 'Gómez Sánchez', false);

-- Insertar roles para TRABAJADORES
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174001', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174002', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174003', 'TRABAJADOR');

-- Insertar en trabajador
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174001', 'Técnico en reacondicionamiento');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174002', 'Técnico en pruebas');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174003', 'Técnico en ventas');

-- Insertar más TRABAJADORES
INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174010', 'sruiz', 'sruiz@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Sofía', 'Ruiz Martínez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174011', 'jperez', 'jperez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Javier', 'Pérez López', false);

-- Insertar roles para los nuevos TRABAJADORES
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174010', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174011', 'TRABAJADOR');

-- Insertar en trabajador
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174010', 'Técnico en logística');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174011', 'Técnico en atención al cliente');

-- Insertar en user_entity CLIENTES
INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174004', 'carlosm', 'carlosm@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Carlos', 'Martínez Gómez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174005', 'laurap', 'laurap@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Laura', 'Pérez Domínguez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174006', 'martinh', 'martinh@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Martín', 'Hernández Ruiz', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174007', 'andreag', 'andreag@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Andrea', 'García López', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174008', 'fernandor', 'fernandor@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Fernando', 'Ramírez Ortega', false);

-- Insertar más CLIENTES
INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174020', 'mariag', 'mariag@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'María', 'González Sánchez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174021', 'pedrol', 'pedrol@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Pedro', 'López Martínez', false);

-- Insertar roles para CLIENTES
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174004', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174005', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174006', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174007', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174008', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174020', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174021', 'CLIENTE');

-- Insertar en cliente
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174004');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174005');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174006');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174007');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174008');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174020');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174021');

-- Insertar en producto
INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (1, 'iPhone 12', 'Teléfono reacondicionado en excelente estado', 499.99, 'imagen1.jpg', '223e4567-e89b-12d3-a456-426614174004', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (2, 'Samsung Galaxy S21', 'Smartphone de alto rendimiento', 599.99, 'imagen2.jpg', '223e4567-e89b-12d3-a456-426614174004', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (3, 'MacBook Pro 13"', 'Laptop para trabajo y diseño', 1299.99, 'imagen3.jpg', '223e4567-e89b-12d3-a456-426614174006', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (4, 'Monitor LG 4K', 'Monitor UHD para productividad', 299.99, 'imagen4.jpg', '223e4567-e89b-12d3-a456-426614174006', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (5, 'Teclado Mecánico', 'Teclado gaming RGB', 99.99, 'imagen5.jpg', '223e4567-e89b-12d3-a456-426614174008', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (6, 'iPad Pro 12.9"', 'Tablet de alta gama para profesionales', 899.99, 'imagen6.jpg', '223e4567-e89b-12d3-a456-426614174005', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (7, 'Samsung Galaxy Tab S7', 'Tablet con lápiz óptico incluido', 699.99, 'imagen7.jpg', '223e4567-e89b-12d3-a456-426614174007', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (8, 'Apple Watch Series 6', 'Reloj inteligente con monitor de oxígeno en sangre', 399.99, 'imagen8.jpg', '223e4567-e89b-12d3-a456-426614174008', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (9, 'Dell XPS 15', 'Laptop ultraportátil con pantalla InfinityEdge', 1499.99, 'imagen9.jpg', '223e4567-e89b-12d3-a456-426614174004', NULL, false);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante, deleted)
VALUES (10, 'Sony WH-1000XM4', 'Auriculares con cancelación de ruido', 349.99, 'imagen10.jpg', '223e4567-e89b-12d3-a456-426614174006', NULL, false);

ALTER SEQUENCE producto_seq RESTART WITH 60;

-- Insertar en categoria
INSERT INTO categoria (id, nombre, deleted) VALUES (1, 'Smartphones', false);
INSERT INTO categoria (id, nombre, deleted) VALUES (2, 'Laptops', false);
INSERT INTO categoria (id, nombre, deleted) VALUES (3, 'Tablets', false);
INSERT INTO categoria (id, nombre, deleted) VALUES (4, 'Accesorios', false);
INSERT INTO categoria (id, nombre, deleted) VALUES (5, 'Televisores', false);
INSERT INTO categoria (id, nombre, deleted) VALUES (6, 'Wearables', false);
INSERT INTO categoria (id, nombre, deleted) VALUES (7, 'Audio', false);
INSERT INTO categoria (id, nombre, deleted) VALUES (8, 'Gaming', false);

ALTER SEQUENCE categoria_seq RESTART WITH 58;

-- Insertar en tiene (relación entre productos y categorías)
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (1, 1);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (1, 2);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (2, 3);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (2, 4);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (4, 5);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (3, 6);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (3, 7);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (6, 8);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (2, 9);
INSERT INTO tiene(categoria_id_producto, producto_id_categoria) VALUES (7, 10);

-- Insertar en organizacion
INSERT INTO organizacion (id, nombre, direccion, trabajador_id, deleted) VALUES (1, 'Green Earth', 'Calle 123, Madrid', '123e4567-e89b-12d3-a456-426614174001', false);
INSERT INTO organizacion (id, nombre, direccion, trabajador_id, deleted) VALUES (2, 'EcoFuture', 'Avenida 45, Barcelona', '123e4567-e89b-12d3-a456-426614174001', false);
INSERT INTO organizacion (id, nombre, direccion, trabajador_id, deleted) VALUES (3, 'RecyTech', 'Plaza Mayor, Sevilla', '123e4567-e89b-12d3-a456-426614174002', false);
INSERT INTO organizacion (id, nombre, direccion, trabajador_id, deleted) VALUES (4, 'EcoRecycle', 'Calle 67, Valencia', '123e4567-e89b-12d3-a456-426614174010', false);
INSERT INTO organizacion (id, nombre, direccion, trabajador_id, deleted) VALUES (5, 'GreenTech', 'Avenida 89, Bilbao', '123e4567-e89b-12d3-a456-426614174011', false);

ALTER SEQUENCE organizacion_seq RESTART WITH 55;

-- Insertar en valora
INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (1, '123e4567-e89b-12d3-a456-426614174001', 5, 'Excelente producto, como nuevo.');

INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (2, '123e4567-e89b-12d3-a456-426614174001', 4, 'Muy buen estado, aunque la batería dura menos de lo esperado.');

INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (3, '123e4567-e89b-12d3-a456-426614174002', 5, 'Rápido y eficiente, perfecto para mi trabajo.');

INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (4, '123e4567-e89b-12d3-a456-426614174001', 3, 'Buena calidad, pero los colores no son tan vivos como esperaba.');

INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (5, '123e4567-e89b-12d3-a456-426614174001', 5, 'Teclado muy cómodo y con una iluminación increíble.');

INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (6, '123e4567-e89b-12d3-a456-426614174001', 4, 'Gran dispositivo, aunque algo pesado.');

INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (7, '123e4567-e89b-12d3-a456-426614174001', 5, 'Perfecto para tomar notas y dibujar.');

INSERT INTO valora (producto_id, trabajador_id, puntuacion, comentario)
VALUES (8, '123e4567-e89b-12d3-a456-426614174002', 4, 'Muy útil, aunque la batería podría durar más.');


