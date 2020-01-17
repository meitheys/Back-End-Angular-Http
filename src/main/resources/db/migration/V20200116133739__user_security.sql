create table user_auth
(
id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(150) NOT NULL
);

create unique index ix_user_auth_01 on user_auth (email asc);

create table role
(
id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
role_name VARCHAR(50) NOT NULL
);

create unique index ix_role_01 on role (role_name asc);

INSERT INTO role (role_name) VALUES ('ROLE_USER');
INSERT INTO role (role_name) VALUES ('ROLE_PM');
INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');

create table user_roles
(
    user_id BIGINT FOREIGN KEY REFERENCES user_auth(id),
    role_id BIGINT FOREIGN KEY REFERENCES role(id)
);

create index ix_user_roles_01 on user_roles(user_id asc);
create index ix_user_roles_02 on user_roles(role_id asc);