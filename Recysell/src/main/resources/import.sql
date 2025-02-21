INSERT INTO user_entity (id, username, email, password, enabled) VALUES ( '123e4567-e89b-12d3-a456-426614174000','admin', 'admin@localhost', '{bcrypt}$2y$10$W257cv0HtGxVEbh6wZ/9MuIW/zqMqxUWfC.M0iFL3FBzoCO4ruQZW', true);

INSERT INTO user_roles (user_id, roles)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'ADMIN');