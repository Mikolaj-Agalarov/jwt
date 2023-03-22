create table roles
(
    id bigserial not null unique primary key,
    role varchar not null unique
);

insert into roles(role) values ('ROLE_ADMIN');

insert into roles(role) values ('ROLE_USER');

