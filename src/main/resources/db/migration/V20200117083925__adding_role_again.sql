create table roles
(
id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
role_name VARCHAR(50) NOT NULL
);

create unique index ix_roles_01 on roles (role_name asc);