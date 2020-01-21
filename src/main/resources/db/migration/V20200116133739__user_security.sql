create table users
(
id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(150) NOT NULL
);

create unique index ix_users__01 on users (email asc);

create table roles
(
id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL
);

create unique index ix_role_01 on roles (name asc);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_PM');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

create table user_roles
(
    user_id BIGINT FOREIGN KEY REFERENCES users(id),
    role_id BIGINT FOREIGN KEY REFERENCES roles(id)
);

create index ix_user_roles_01 on user_roles(user_id asc);
create index ix_user_roles_02 on user_roles(role_id asc);