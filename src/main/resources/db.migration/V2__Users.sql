create table users
(
    id bigserial not null unique primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null unique,
    role_id bigint references roles (id)
)
