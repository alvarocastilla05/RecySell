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

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174010', 'sruiz', 'sruiz@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Sofía', 'Ruiz Martínez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174011', 'jperez', 'jperez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Javier', 'Pérez López', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('123e4567-e89b-12d3-a456-426614174012', 'trabajador', 'jperez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Trabajador', 'Trabajador', false);

-- Insertar roles para TRABAJADORES
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174001', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174002', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174003', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174010', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174011', 'TRABAJADOR');
INSERT INTO user_roles (user_id, roles) VALUES ('123e4567-e89b-12d3-a456-426614174012', 'TRABAJADOR');


-- Insertar en trabajador
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174001', 'Técnico en reacondicionamiento');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174002', 'Técnico en pruebas');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174003', 'Técnico en ventas');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174010', 'Técnico en logística');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174011', 'Técnico en atención al cliente');
INSERT INTO trabajador (id, puesto) VALUES ('123e4567-e89b-12d3-a456-426614174012', 'Técnico en reacondicionamiento');



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

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174020', 'mariag', 'mariag@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'María', 'González Sánchez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174021', 'pedrol', 'pedrol@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Pedro', 'López Martínez', false);

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos, deleted)
VALUES ('223e4567-e89b-12d3-a456-426614174022', 'user', 'user@gmail.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'user', 'user user', false);

-- Insertar roles para CLIENTES
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174004', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174005', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174006', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174007', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174008', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174020', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174021', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174022', 'CLIENTE');


-- Insertar en cliente
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174004');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174005');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174006');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174007');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174008');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174020');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174021');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174022');



-- Insertar en categoria
INSERT INTO categoria (id, nombre, imagen, deleted) VALUES (1, 'Smartphones', 'movil.jpg', false);
INSERT INTO categoria (id, nombre, imagen, deleted) VALUES (2, 'Laptops','ordenadorLogo.jpg', false);
INSERT INTO categoria (id, nombre, imagen, deleted) VALUES (3, 'Tablets','tablet.jpg', false);
INSERT INTO categoria (id, nombre, imagen, deleted) VALUES (5, 'Televisores','televisor.jpg', false);
INSERT INTO categoria (id, nombre, imagen, deleted) VALUES (6, 'Wearables','wearables.jpg', false);
INSERT INTO categoria (id, nombre, imagen, deleted) VALUES (7, 'Audio', 'audio.jpg',false);
INSERT INTO categoria (id, nombre, imagen, deleted) VALUES (8, 'Gaming', 'gaming.jpg',false);

ALTER SEQUENCE categoria_seq RESTART WITH 58;

--Insertar productos
INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (1, 'iPhone 12', 'Teléfono reacondicionado en excelente estado', 499.99, '223e4567-e89b-12d3-a456-426614174004', NULL, false, '2023-10-01 10:00:00', 'COMO_NUEVO', 1, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (2, 'Samsung Galaxy S21', 'Smartphone de alto rendimiento', 599.99, '223e4567-e89b-12d3-a456-426614174004', NULL, false, '2023-10-02 11:00:00', 'BUEN_ESTADO', 1, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (3, 'MacBook Pro 13"', 'Laptop para trabajo y diseño', 1299.99, '223e4567-e89b-12d3-a456-426614174006', NULL, false, '2023-10-03 12:00:00', 'ESTADO_REGULAR', 2, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (4, 'Monitor LG 4K', 'Monitor UHD para productividad', 299.99, '223e4567-e89b-12d3-a456-426614174006', NULL, false, '2023-10-04 13:00:00', 'BUEN_ESTADO', 5, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (5, 'Teclado Mecánico', 'Teclado gaming RGB', 99.99, '223e4567-e89b-12d3-a456-426614174008', NULL, false, '2023-10-05 14:00:00', 'COMO_NUEVO', 8, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (6, 'iPad Pro 12.9"', 'Tablet de alta gama para profesionales', 899.99, '223e4567-e89b-12d3-a456-426614174005', NULL, false, '2023-10-06 15:00:00', 'CON_DANIOS', 3, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (7, 'Samsung Galaxy Tab S7', 'Tablet con lápiz óptico incluido', 699.99, '223e4567-e89b-12d3-a456-426614174007', NULL, false, '2023-10-07 16:00:00', 'BUEN_ESTADO', 3, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (8, 'Apple Watch Series 6', 'Reloj inteligente con monitor de oxígeno en sangre', 399.99, '223e4567-e89b-12d3-a456-426614174008', NULL, false, '2023-10-08 17:00:00', 'COMO_NUEVO', 6, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (9, 'Dell XPS 15', 'Laptop ultraportátil con pantalla InfinityEdge', 1499.99, '223e4567-e89b-12d3-a456-426614174004', NULL, false, '2023-10-09 18:00:00', 'ESTADO_REGULAR', 2, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (10, 'Sony WH-1000XM4', 'Auriculares con cancelación de ruido', 349.99, '223e4567-e89b-12d3-a456-426614174006', NULL, false, '2023-10-10 19:00:00', 'BUEN_ESTADO', 7, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (11, 'Huawei P30', 'Smartphone con excelente cámara', 399.99, NULL, '223e4567-e89b-12d3-a456-426614174004', false, '2023-10-11 10:00:00', 'BUEN_ESTADO', 1, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (12, 'Lenovo ThinkPad X1', 'Laptop empresarial de alto rendimiento', 1299.99, NULL, '223e4567-e89b-12d3-a456-426614174004', false, '2023-10-12 11:00:00', 'COMO_NUEVO', 2, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (13, 'Amazon Echo Dot', 'Altavoz inteligente con Alexa', 49.99, NULL, '223e4567-e89b-12d3-a456-426614174004', false, '2023-10-13 12:00:00', 'COMO_NUEVO', 7, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (14, 'Sony PlayStation 5', 'Consola de videojuegos de última generación', 499.99, NULL, '223e4567-e89b-12d3-a456-426614174004', false, '2023-10-14 13:00:00', 'COMO_NUEVO', 8, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (15, 'Samsung Galaxy Watch 4', 'Reloj inteligente con funciones avanzadas', 299.99, NULL, '223e4567-e89b-12d3-a456-426614174004', false, '2023-10-15 14:00:00', 'BUEN_ESTADO', 6, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (16, 'Google Pixel 6', 'Smartphone con excelente rendimiento y cámara', 599.99, '223e4567-e89b-12d3-a456-426614174005', NULL, false, '2023-10-16 10:00:00', 'COMO_NUEVO', 1, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (17, 'Microsoft Surface Pro 7', 'Tablet convertible ideal para trabajo', 899.99, '223e4567-e89b-12d3-a456-426614174006', NULL, false, '2023-10-17 11:00:00', 'BUEN_ESTADO', 2, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (18, 'Sony Bravia 55"', 'Televisor 4K con HDR', 799.99, '223e4567-e89b-12d3-a456-426614174007', NULL, false, '2023-10-18 12:00:00', 'COMO_NUEVO', 5, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (19, 'Logitech MX Master 3', 'Ratón inalámbrico avanzado', 99.99, '223e4567-e89b-12d3-a456-426614174008', NULL, false, '2023-10-19 13:00:00', 'COMO_NUEVO', 8, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (20, 'Samsung Galaxy Buds Pro', 'Auriculares inalámbricos con cancelación de ruido', 199.99, '223e4567-e89b-12d3-a456-426614174004', NULL, false, '2023-10-20 14:00:00', 'BUEN_ESTADO', 7, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (21, 'Dell Inspiron 15', 'Laptop para uso diario', 499.99, '223e4567-e89b-12d3-a456-426614174005', NULL, false, '2023-10-21 15:00:00', 'ESTADO_REGULAR', 2, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (22, 'Apple AirPods Pro', 'Auriculares con cancelación de ruido', 249.99, '223e4567-e89b-12d3-a456-426614174006', NULL, false, '2023-10-22 16:00:00', 'COMO_NUEVO', 7, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (23, 'HP Pavilion Gaming', 'Laptop para gaming', 799.99, '223e4567-e89b-12d3-a456-426614174007', NULL, false, '2023-10-23 17:00:00', 'BUEN_ESTADO', 2, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (24, 'Fitbit Versa 3', 'Reloj inteligente para fitness', 199.99, '223e4567-e89b-12d3-a456-426614174008', NULL, false, '2023-10-24 18:00:00', 'COMO_NUEVO', 6, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (25, 'Nintendo Switch OLED', 'Consola portátil con pantalla mejorada', 349.99, '223e4567-e89b-12d3-a456-426614174004', NULL, false, '2023-10-25 19:00:00', 'COMO_NUEVO', 8, true);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (26, 'HP EliteBook 840', 'Laptop empresarial con diseño compacto', 899.99, '223e4567-e89b-12d3-a456-426614174005', NULL, false, '2023-10-26 10:00:00', 'BUEN_ESTADO', 2, false);

INSERT INTO producto (id, nombre, descripcion, precio, cliente_id_vendedor, cliente_id_donante, deleted, fecha_registro, estado, categoria_id, disponibilidad)
VALUES (27, 'Asus ROG Zephyrus G14', 'Laptop gaming de alto rendimiento', 1499.99, '223e4567-e89b-12d3-a456-426614174006', NULL, false, '2023-10-27 11:00:00', 'COMO_NUEVO', 2, false);

ALTER SEQUENCE producto_seq RESTART WITH 77;

-- Insert images for products
INSERT INTO producto_imagenes (producto_id, imagen_url)
VALUES
    ( 1, 'movilProducto.jpg'), -- iPhone 12
    ( 4, 'televisorProducto.jpeg'), -- Monitor LG 4K
    ( 3, 'ordenadorProducto.jpg'), -- MacBook Pro 13"
    ( 9, 'ordenadorProducto.jpg'), -- Dell XPS 15
    ( 26, 'ordenadorProducto.jpg'),
    (6, 'tabletProducto.jpg'),
    (7, 'tabletProducto.jpg'),
    (8, 'reloj.jpg'),
    (15, 'reloj.jpg'),
    (24, 'reloj.jpg'),
    (25, 'nintendo.jpg'),
    (10, 'audioProducto.jpeg'), -- Sony WH-1000XM4
    (11, 'movilProducto.jpg'), -- Huawei P30
    (12, 'ordenadorProducto.jpg'), -- Lenovo ThinkPad X1
    (13, 'audioProducto.jpeg'), -- Amazon Echo Dot
    (16, 'movilProducto.jpg'), -- Google Pixel 6
    (17, 'ordenadorProducto.jpg'), -- Microsoft Surface Pro 7
    (18, 'televisorProducto.jpeg'), -- Sony Bravia 55"
    (19, 'audioProducto.jpeg'), -- Logitech MX Master 3
    (20, 'audioProducto.jpeg'), -- Samsung Galaxy Buds Pro
    (21, 'ordenadorProducto.jpg'), -- Dell Inspiron 15
    (22, 'audioProducto.jpeg'), -- Apple AirPods Pro
    (23, 'ordenadorProducto.jpg'), -- HP Pavilion Gaming
    (24, 'reloj.jpg'), -- Fitbit Versa 3
    (25, 'gamingProducto.jpg'), -- Nintendo Switch OLED
    ( 27, 'ordenadorProducto.jpg');








-- Insertar en organizacion
INSERT INTO organizacion (id, nombre, descripcion, direccion, trabajador_id, deleted)
VALUES (1, 'Green Earth', 'Organización dedicada a la reforestación y conservación del medio ambiente.', 'Calle 123, Madrid', '123e4567-e89b-12d3-a456-426614174001', false);

INSERT INTO organizacion (id, nombre, descripcion, direccion, trabajador_id, deleted)
VALUES (2, 'EcoFuture', 'Promueve el uso de tecnologías sostenibles para un futuro más ecológico.', 'Avenida 45, Barcelona', '123e4567-e89b-12d3-a456-426614174001', false);

INSERT INTO organizacion (id, nombre, descripcion, direccion, trabajador_id, deleted)
VALUES (3, 'RecyTech', 'Especializada en el reciclaje de dispositivos electrónicos y tecnológicos.', 'Plaza Mayor, Sevilla', '123e4567-e89b-12d3-a456-426614174002', false);

INSERT INTO organizacion (id, nombre, descripcion, direccion, trabajador_id, deleted)
VALUES (4, 'EcoRecycle', 'Fomenta la reutilización de materiales para reducir los desechos.', 'Calle 67, Valencia', '123e4567-e89b-12d3-a456-426614174010', false);

INSERT INTO organizacion (id, nombre, descripcion, direccion, trabajador_id, deleted)
VALUES (5, 'GreenTech', 'Desarrolla soluciones tecnológicas para la sostenibilidad ambiental.', 'Avenida 89, Bilbao', '123e4567-e89b-12d3-a456-426614174011', false);

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

INSERT INTO donacion (producto_id, organizacion_id, fecha_donacion) VALUES (10, 2, '2025-03-01 10:00:00');
INSERT INTO donacion (producto_id, organizacion_id, fecha_donacion) VALUES (11, 1, '2025-03-02 15:30:00');
INSERT INTO donacion (producto_id, organizacion_id, fecha_donacion) VALUES (12, 4, '2025-03-05 12:45:00');
INSERT INTO donacion (producto_id, organizacion_id, fecha_donacion) VALUES (13, 3, '2025-03-07 08:20:00');
INSERT INTO donacion (producto_id, organizacion_id, fecha_donacion) VALUES (14, 5, '2025-03-10 18:15:00');


-- Insertar datos en compra
INSERT INTO compra (id, gastos_envio, sub_total, fecha_venta, provincia, codigo_postal, direccion_entrega, cliente_id, deleted, estado_compra)
VALUES (1, 10.0, 0, '2023-10-01 10:00:00', 'Madrid', '28001', 'Calle Gran Vía, 1', '223e4567-e89b-12d3-a456-426614174004', false, 'CARRITO');

INSERT INTO compra (id, gastos_envio, sub_total, fecha_venta, provincia, codigo_postal, direccion_entrega, cliente_id, deleted, estado_compra)
VALUES (2, 15.0, 0, '2023-10-02 11:00:00', 'Barcelona', '08002', 'Avenida Diagonal, 45', '223e4567-e89b-12d3-a456-426614174005', false, 'CARRITO');

INSERT INTO compra (id, gastos_envio, sub_total, fecha_venta, provincia, codigo_postal, direccion_entrega, cliente_id, deleted, estado_compra)
VALUES (3, 8.0, 0, '2023-10-03 12:00:00', 'Sevilla', '41001', 'Plaza Nueva, 10', '223e4567-e89b-12d3-a456-426614174006', false, 'CARRITO');

INSERT INTO compra (id, gastos_envio, sub_total, fecha_venta, provincia, codigo_postal, direccion_entrega, cliente_id, deleted, estado_compra)
VALUES (4, 12.0, 0, '2023-10-04 13:00:00', 'Valencia', '46001', 'Calle Colón, 20', '223e4567-e89b-12d3-a456-426614174007', false, 'CARRITO');

INSERT INTO compra (id, gastos_envio, sub_total, fecha_venta, provincia, codigo_postal, direccion_entrega, cliente_id, deleted, estado_compra)
VALUES (5, 20.0, 0, '2023-10-05 14:00:00', 'Bilbao', '48001', 'Gran Vía, 30', '223e4567-e89b-12d3-a456-426614174008', false, 'CARRITO');

INSERT INTO compra (id, gastos_envio, sub_total, fecha_venta, provincia, codigo_postal, direccion_entrega, cliente_id, deleted, estado_compra)
VALUES (6, 5.0, 2399.98, '2023-10-06 10:00:00', 'Madrid', '28002', 'Calle Alcalá, 10', '223e4567-e89b-12d3-a456-426614174004', false, 'ENTREGADO');

-- Reiniciar la secuencia de compra
ALTER SEQUENCE compra_seq RESTART WITH 56;

-- Insertar datos en lineaVenta
INSERT INTO linea_venta (id ,deleted, compra_id, producto_id_linea_venta)
VALUES (1, false, 1, 7);

INSERT INTO linea_venta (id ,deleted, compra_id, producto_id_linea_venta)
VALUES (2, false, 1, 8);

INSERT INTO linea_venta (id, deleted, compra_id, producto_id_linea_venta)
VALUES (3, false, 2, 9);

INSERT INTO linea_venta (id, deleted, compra_id, producto_id_linea_venta)
VALUES (4, false, 2, 10);

INSERT INTO linea_venta (id, deleted, compra_id, producto_id_linea_venta)
VALUES (5, false, 6, 26);

INSERT INTO linea_venta (id, deleted, compra_id, producto_id_linea_venta)
VALUES (6, false, 6, 27);



-- Reiniciar la secuencia de lineaVenta
ALTER SEQUENCE linea_venta_seq RESTART WITH 61;

-- Eliminar la restricción de clave única en producto_id_linea_venta
ALTER TABLE linea_venta DROP CONSTRAINT IF EXISTS linea_venta_producto_id_linea_venta_key;

-- Agregar restricción compuesta (producto no puede repetirse en misma compra)
ALTER TABLE linea_venta ADD CONSTRAINT linea_venta_unique_compra_producto UNIQUE (compra_id, producto_id_linea_venta);

