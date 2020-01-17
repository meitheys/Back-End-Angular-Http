create table users
(
id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(150) NOT NULL
);

create unique index ix_users_01 on user_auth (email asc);