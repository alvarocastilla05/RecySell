INSERT INTO user_entity (id, username, email, password, enabled) VALUES ( '123e4567-e89b-12d3-a456-426614174000','admin', 'admin@localhost', '{bcrypt}$2y$10$W257cv0HtGxVEbh6wZ/9MuIW/zqMqxUWfC.M0iFL3FBzoCO4ruQZW', true);

-- Insertar en user_entity TRABAJADORES
INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('123e4567-e89b-12d3-a456-426614174001', 'jrodriguez', 'jrodriguez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Juan', 'Rodríguez Pérez');

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('123e4567-e89b-12d3-a456-426614174002', 'mlopez', 'mlopez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'María', 'López Fernández');

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('123e4567-e89b-12d3-a456-426614174003', 'agomez', 'agomez@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Alejandro', 'Gómez Sánchez');

-- Insertar en trabajador
INSERT INTO trabajador (id, puesto)
VALUES ('123e4567-e89b-12d3-a456-426614174001', 'Técnico en reacondicionamiento');

INSERT INTO trabajador (id, puesto)
VALUES ('123e4567-e89b-12d3-a456-426614174002', 'Técnico en pruebas');

INSERT INTO trabajador (id, puesto)
VALUES ('123e4567-e89b-12d3-a456-426614174003', 'Técnico en ventas');

-- Insertar en user_entity CLIENTES
INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('223e4567-e89b-12d3-a456-426614174004', 'carlosm', 'carlosm@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Carlos', 'Martínez Gómez');

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('223e4567-e89b-12d3-a456-426614174005', 'laurap', 'laurap@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Laura', 'Pérez Domínguez');

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('223e4567-e89b-12d3-a456-426614174006', 'martinh', 'martinh@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Martín', 'Hernández Ruiz');

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('223e4567-e89b-12d3-a456-426614174007', 'andreag', 'andreag@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Andrea', 'García López');

INSERT INTO user_entity (id, username, email, password, enabled, nombre, apellidos)
VALUES ('223e4567-e89b-12d3-a456-426614174008', 'fernandor', 'fernandor@recycell.com',
        '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true,
        'Fernando', 'Ramírez Ortega');

-- Insertar roles para CLIENTES
INSERT INTO user_roles (user_id, roles)
VALUES ('223e4567-e89b-12d3-a456-426614174004', 'CLIENTE');

INSERT INTO user_roles (user_id, roles)
VALUES ('223e4567-e89b-12d3-a456-426614174005', 'CLIENTE');

INSERT INTO user_roles (user_id, roles)
VALUES ('223e4567-e89b-12d3-a456-426614174006', 'CLIENTE');

INSERT INTO user_roles (user_id, roles)
VALUES ('223e4567-e89b-12d3-a456-426614174007', 'CLIENTE');

INSERT INTO user_roles (user_id, roles)
VALUES ('223e4567-e89b-12d3-a456-426614174008', 'CLIENTE');

-- Insertar en cliente
INSERT INTO cliente (id)
VALUES ('223e4567-e89b-12d3-a456-426614174004');

INSERT INTO cliente (id)
VALUES ('223e4567-e89b-12d3-a456-426614174005');

INSERT INTO cliente (id)
VALUES ('223e4567-e89b-12d3-a456-426614174006');

INSERT INTO cliente (id)
VALUES ('223e4567-e89b-12d3-a456-426614174007');

INSERT INTO cliente (id)
VALUES ('223e4567-e89b-12d3-a456-426614174008');

