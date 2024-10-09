
INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO role (role_name) VALUES ('ROLE_USER');

INSERT INTO app_user (username, password, email) VALUES ('admin', '$2b$12$ONaPRl.ofaxFs/akGcS9qOzE5EdX6OfyC2mnw3EtkIOiGpSOezasy', 'liu@gmail.com');
INSERT INTO app_user (username, password, email) VALUES ('user', '$2b$12$ONaPRl.ofaxFs/akGcS9qOzE5EdX6OfyC2mnw3EtkIOiGpSOezasy', 'liu2@gmail.com');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);