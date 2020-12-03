create table role
(
	name varchar(255) not null
		constraint role_pkey
			primary key
);

alter table role owner to postgres;

create table user_roles
(
    user_id bigint not null
        references users,
    role_name varchar(255) not null
        references role,
    constraint user_roles_pkey
        primary key (user_id)
);

alter table user_roles owner to postgres;

create table users
(
	id bigint not null
		constraint users_pkey
			primary key,
	cic varchar(14) not null,
	date_of_birth timestamp not null,
	email varchar(255) not null
		constraint uk_6dotkott2kjsp8vw4d0m25fb7
			unique,
	first_name varchar(255) not null,
	gender integer not null,
	last_name varchar(255) not null,
	password varchar(255) not null
);

alter table users owner to postgres;

INSERT INTO public.role (name) VALUES ('DEFAULT');
INSERT INTO public.role (name) VALUES ('ADMIN');
INSERT INTO public.role (name) VALUES ('SUPER');

INSERT INTO public.user_roles (user_id, role_name) VALUES ('jherson_k-f@hotmail.com', 'SUPER');
INSERT INTO public.users (id, cic, date_of_birth, email, first_name, gender, last_name, password) VALUES (1, '02778078240', '2020-11-30 16:25:54.103000', 'jherson_k-f@hotmail.com', 'Jherson', 0, 'Haryson', '$2y$12$qv4MDEtGvnlVw95.i850bOS.PphxbNZQ8nikb/GcURjsk8AVQyX8m');


