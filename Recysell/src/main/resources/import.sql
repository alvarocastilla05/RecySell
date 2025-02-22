INSERT INTO user_entity (id, username, email, password, enabled) VALUES ( '123e4567-e89b-12d3-a456-426614174000','admin', 'admin@localhost', '{bcrypt}$2y$10$W257cv0HtGxVEbh6wZ/9MuIW/zqMqxUWfC.M0iFL3FBzoCO4ruQZW', true);

-- Insertar en user_entity TRABAJADORES
INSERT INTO user_entity (id, username, email, password, enabled)
    VALUES ('123e4567-e89b-12d3-a456-426614174001', 'jrodriguez', 'jrodriguez@recycell.com', '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true);

INSERT INTO user_entity (id, username, email, password, enabled)
    VALUES ('123e4567-e89b-12d3-a456-426614174002', 'mlopez', 'mlopez@recycell.com', '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true);

INSERT INTO user_entity (id, username, email, password, enabled)
    VALUES ('123e4567-e89b-12d3-a456-426614174003', 'agomez', 'agomez@recycell.com', '{bcrypt}$2y$10$ORcen3QQGVAYlE5kQ3ytG.v7dXqF4.7dpQX043x/rFPvzysNiugLe', true);




INSERT INTO user_roles (user_id, roles)
    VALUES ('123e4567-e89b-12d3-a456-426614174000', 'ADMIN');

INSERT INTO user_roles (user_id, roles)
    VALUES ('123e4567-e89b-12d3-a456-426614174001', 'TRABAJADOR');

INSERT INTO user_roles (user_id, roles)
    VALUES ('123e4567-e89b-12d3-a456-426614174002', 'TRABAJADOR');

INSERT INTO user_roles (user_id, roles)
    VALUES ('123e4567-e89b-12d3-a456-426614174003', 'TRABAJADOR');


-- Insertar en trabajador
INSERT INTO trabajador (id, puesto)
VALUES ('123e4567-e89b-12d3-a456-426614174001', 'Técnico en reacondicionamiento');

INSERT INTO trabajador (id, puesto)
VALUES ('123e4567-e89b-12d3-a456-426614174002', 'Técnico en pruebas');

INSERT INTO trabajador (id, puesto)
VALUES ('123e4567-e89b-12d3-a456-426614174003', 'Técnico en ventas');