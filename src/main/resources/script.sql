create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
 token_id VARCHAR(256),
 token bytea,
 authentication bytea
);

INSERT into roles (id_rol, name, description) VALUES (1, 'ADMIN', 'Administrator');
INSERT INTO roles (id_rol, name, description) VALUES (2, 'USER', 'User');
INSERT INTO roles (id_rol, name, description) VALUES (3, 'DBA', 'Administrator database');