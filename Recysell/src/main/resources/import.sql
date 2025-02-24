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

-- Insertar roles para CLIENTES
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174004', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174005', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174006', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174007', 'CLIENTE');
INSERT INTO user_roles (user_id, roles) VALUES ('223e4567-e89b-12d3-a456-426614174008', 'CLIENTE');

-- Insertar en cliente
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174004');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174005');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174006');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174007');
INSERT INTO cliente (id) VALUES ('223e4567-e89b-12d3-a456-426614174008');


INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante)
VALUES (1, 'iPhone 12', 'Teléfono reacondicionado en excelente estado', 499.99, 'imagen1.jpg', '223e4567-e89b-12d3-a456-426614174004', NULL);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante)
VALUES (2, 'Samsung Galaxy S21', 'Smartphone de alto rendimiento', 599.99, 'imagen2.jpg', '223e4567-e89b-12d3-a456-426614174004', NULL);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante)
VALUES (3, 'MacBook Pro 13"', 'Laptop para trabajo y diseño', 1299.99, 'imagen3.jpg', '223e4567-e89b-12d3-a456-426614174006', NULL);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante)
VALUES (4, 'Monitor LG 4K', 'Monitor UHD para productividad', 299.99, 'imagen4.jpg', '223e4567-e89b-12d3-a456-426614174006', NULL);

INSERT INTO producto (id, nombre, descripcion, precio, imagen, cliente_id_vendedor, cliente_id_donante)
VALUES (5, 'Teclado Mecánico', 'Teclado gaming RGB', 99.99, 'imagen5.jpg', '223e4567-e89b-12d3-a456-426614174008', NULL);

