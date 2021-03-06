create table if not exists   flyway_schema_history
(
    installed_rank integer not null
        constraint flyway_schema_history_pk
            primary key,
    version varchar(50),
    description varchar(200) not null,
    type varchar(20) not null,
    script varchar(1000) not null,
    checksum integer,
    installed_by varchar(100) not null,
    installed_on timestamp default now() not null,
    execution_time integer not null,
    success boolean not null
);


create table if not exists dependent_profile
(
    id bigint not null
        constraint dependent_profile_pkey
            primary key,
    create_at timestamp,
    situation char,
    date_of_birth timestamp not null,
    first_name varchar(255) not null,
    gender varchar(6) not null,
    last_name varchar(255) not null,
    cic varchar(14),
    image varchar(7) not null,
    profile varchar(255)
);

create table if not exists campaign
(
    id bigint not null
        constraint campaign_pkey
            primary key,
    create_at timestamp,
    situation char,
    description varchar(255),
    effective_date timestamp,
    expire_date timestamp,
    image varchar(255),
    title varchar(255),
    professional_profile_id bigint
        constraint fkogwwwbrk07s7x5aqli08vab2y
            references dependent_profile
);


create table if not exists campaign_patient_id
(
    campaign_id bigint not null
        constraint fk32ec8d7c77ug89aikkvhtk6lm
            references campaign,
    patient_id bigint not null
        constraint uk_sddfo0onwbp47uguhioq4a7hi
            unique
        constraint fkn71to508m2ldlbrht8i8k4yts
            references dependent_profile,
    constraint campaign_patient_id_pkey
        primary key (campaign_id, patient_id)
);


create table if not exists hibernate_sequences
(
    sequence_name varchar(255) not null
        constraint hibernate_sequences_pkey
            primary key,
    next_val bigint
);

create table if not exists place
(
    id bigint not null
        constraint place_pkey
            primary key,
    create_at timestamp,
    situation char,
    amount integer,
    applied integer,
    description varchar(255),
    latitude varchar(255),
    longitude varchar(255),
    name varchar(255)
);


create table if not exists campaign_place_id
(
    campaign_id bigint not null
        constraint fkemvohmp7r19p6f92a5kgsqxuy
            references campaign,
    place_id bigint not null
        constraint uk_i751hab44rgpltpxe9iur1cmw
            unique
        constraint fkksu07rsr9owlqtl9lt4r3hel7
            references place,
    constraint campaign_place_id_pkey
        primary key (campaign_id, place_id)
);

create table if not exists role
(
    name varchar(255) not null
        constraint role_pkey
            primary key
);

create table if not exists users
(
    id bigint not null
        constraint users_pkey
            primary key,
    create_at timestamp,
    situation char,
    date_of_birth timestamp not null,
    first_name varchar(255) not null,
    gender varchar(6) not null,
    last_name varchar(255) not null,
    cic varchar(14) not null,
    email varchar(255) not null
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    password varchar(255) not null
);

create table if not exists user_dependent_profile
(
    user_id bigint not null
        constraint fkks75c4drggrv3xjkagat62ddr
            references users,
    dependent_profile_id bigint not null
        constraint uk_3pyvb9di4dl4jn9k1d9owml0l
            unique
        constraint fk47pgr2dotjvcrtl3fylrwn5pw
            references dependent_profile,
    constraint user_dependent_profile_pkey
        primary key (user_id, dependent_profile_id)
);


create table if not exists user_role
(
    user_id bigint not null
        constraint fkj345gk1bovqvfame88rcx7yyx
            references users,
    role_name varchar(255) not null
        constraint fkn6r4465stkbdy93a9p8cw7u24
            references role,
    constraint user_role_pkey
        primary key (user_id, role_name)
);

create table if not exists vaccine
(
    id bigint not null
        constraint vaccine_pkey
            primary key,
    create_at timestamp,
    situation char,
    dosage varchar(255),
    final_range integer,
    frequency integer,
    initial_range integer,
    name varchar(255),
    next_dosage integer,
    observation varchar(500),
    prevented_diseases varchar(255),
    range varchar(20) not null,
    requirement varchar(16),
    next_vaccine_id bigint
        constraint fk3wyt8t5a41vbg3370mg8diihs
            references vaccine
);

create table if not exists campaign_vaccine_id
(
    campaign_id bigint not null
        constraint fkrnxjln9hoyg72afefb94vo9rb
            references campaign,
    vaccine_id bigint not null
        constraint uk_lr6vdqw2r36ew2xnimdjk0ddw
            unique
        constraint fktookg9x00m8r06t0bjwshe0wj
            references vaccine,
    constraint campaign_vaccine_id_pkey
        primary key (campaign_id, vaccine_id)
);

create table if not exists dependent_profile_vaccine
(
    create_at timestamp,
    situation char,
    dependent_profile_id bigint not null
        constraint fkdry2i93hjm415xaybnb35ifaf
            references dependent_profile,
    vaccine_id bigint not null
        constraint fkf7u59jhu2c4mtwf81rc8o55n2
            references vaccine,
    professional_profile_id bigint not null
        constraint fkiikgmp87xubk57xj1su3dowcp
            references dependent_profile,
    constraint dependent_profile_vaccine_pkey
        primary key (dependent_profile_id, vaccine_id)
);

create table if not exists history
(
    id bigint not null
        constraint history_pkey
            primary key,
    create_at timestamp,
    situation char,
    transaction_id varchar(255) not null,
    campaign_id bigint
        constraint fkik1wpcgdymt0d2j7i4ena2g1e
            references campaign,
    patient_id bigint
        constraint fkn3s829h8a0ep61n6wq450a85p
            references dependent_profile,
    professional_id bigint
        constraint fkdqvd59lo8q2tg5et4ypw4j48r
            references dependent_profile,
    vaccine_id bigint
        constraint fkej2e36bldp04cq5a0b6646aym
            references vaccine
);





INSERT INTO role (name) VALUES ('DEFAULT');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('SUPER');

INSERT INTO users (id, cic, date_of_birth, email, first_name, gender, last_name, password) VALUES (1, '02778078240', '2020-11-30 16:25:54.103000', 'jherson_k-f@hotmail.com', 'Jherson', 'MALE', 'Haryson', '$2y$12$qv4MDEtGvnlVw95.i850bOS.PphxbNZQ8nikb/GcURjsk8AVQyX8m');
INSERT INTO users (id, cic, date_of_birth, email, first_name, gender, last_name, password) VALUES (2, '02778078240', '2020-11-30 16:25:54.103000', 'danilo.duarte8@hotmail.com', 'Danilo', 'MALE', 'Duarte', '$2y$12$qv4MDEtGvnlVw95.i850bOS.PphxbNZQ8nikb/GcURjsk8AVQyX8m');

INSERT INTO user_role (user_id, role_name) VALUES (1, 'SUPER');
INSERT INTO user_role (user_id, role_name) VALUES (2, 'SUPER');

INSERT INTO dependent_profile (id, create_at, situation, date_of_birth, first_name, gender, last_name, cic, image, profile) VALUES (1, '2020-12-04 19:02:11.284000', 'A', '2020-12-04 19:02:03.566000', 'jherson', 'MALE', 'haryson', null, 'DEFAULT', null);
INSERT INTO dependent_profile (id, create_at, situation, date_of_birth, first_name, gender, last_name, cic, image, profile) VALUES (2, '2020-12-04 19:43:58.425000', 'A', '2020-12-04 19:43:48.957000', 'danilo', 'MALE', 'duarte', null, 'DEFAULT', null);

INSERT INTO user_dependent_profile (user_id, dependent_profile_id) VALUES (1, 1);
INSERT INTO user_dependent_profile (user_id, dependent_profile_id) VALUES (2, 2);

/* VACINES */
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (1, 'DOSAGE_UNIQUE', 0, null, 0, 'BCG-ID', null, null, 'Formas graves de Tuberculose', 'INFANT', null, '2020-12-04 17:28:36.361000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (2, 'DOSAGE_1', 0, null, 0, 'Vacina contra Hepatite B', 1, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 3, '2020-12-04 17:28:34.525000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (3, 'DOSAGE_2', 1, null, 1, 'Vacina contra Hepatite B', 5, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 17, '2020-12-04 17:28:33.728000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (4, 'DOSAGE_1', 2, null, 2, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 9, '2020-12-04 17:28:32.243000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (5, 'DOSAGE_1', 2, null, 2, 'Vacina Oral contra Rotavírus Humano', 2, 'É possível administar a primeira dose da Vacina Oral de Rotavírus Humano a partir de 1 mês e 15 dias a 3 meses e 7 dias de idade (6 a 14 semanas de vida)', 'Doenças diarréicas por Rotavírus', 'INFANT', 11, '2020-12-04 17:28:33.024000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (6, 'DOSAGE_1', 2, null, 2, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 12, '2020-12-04 17:28:30.607000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (7, 'DOSAGE_1', 2, null, 2, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 10, '2020-12-04 17:28:31.458000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (8, 'DOSAGE_1', 3, null, 3, 'Vacina contra Meningococo C', 2, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 13, '2020-12-04 17:28:29.793000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (9, 'DOSAGE_2', 4, null, 4, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 14, '2020-12-04 17:28:26.400000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (10, 'DOSAGE_2', 4, null, 4, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 16, '2020-12-04 17:28:28.088000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (11, 'DOSAGE_2', 4, null, 4, 'Vacina Oral contra Rotavírus Humano', null, 'É possível administrar a segunda dose da Vacina Oral de Rotavírus Humano a partir de 3 meses e 7 dias a 5 meses e 15 dias de idade (14 a 24 semanas de ida). O intervalo mínimo reconizado entre a primeira e a segunda dose é de 4 semanas.', 'Doenças diarréicas por Rotavírus', 'INFANT', null, '2020-12-04 17:28:28.952000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (12, 'DOSAGE_2', 4, null, 4, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 15, '2020-12-04 17:28:27.262000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (13, 'DOSAGE_2', 5, null, 5, 'Vacina contra Meningococo C', 10, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 22, '2020-12-04 17:28:24.577000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (14, 'DOSAGE_3', 6, null, 6, 'Vacina Tetravalente', 9, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 23, '2020-12-04 17:27:56.488000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (15, 'DOSAGE_3', 6, null, 6, 'Vacina contra Pneumococos', 6, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 20, '2020-12-04 17:27:49.335000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (16, 'DOSAGE_3', 6, null, 6, 'Vacina Oral contra a Pólio', 9, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 21, '2020-12-04 17:28:04.691000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (17, 'DOSAGE_2', 6, null, 6, 'Vacina contra Hepatite B', null, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', null, '2020-12-04 17:28:16.011000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (18, 'DOSAGE_1', 9, null, 9, 'Vacina contra febre amarela', null, 'A vacina contra febre amarela está indicada para crianças a partir dos 09 meses de idade e quando for viajar para o exterior, vacinar contra Febre Amarela 10 (dez) dias antes da viagem.', 'Febre amarela', 'INFANT', null, '2020-12-04 17:27:40.540000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (19, 'DOSAGE_1', 12, null, 12, 'Tríplice Viral', 36, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', 25, '2020-12-04 17:27:26.286000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (20, 'DOSAGE_REINFORCEMENT', 12, null, 12, 'Vacina contra Pneumococos', null, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', null, '2020-12-04 17:27:32.960000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (21, 'DOSAGE_REINFORCEMENT', 15, null, 15, 'Vacina Oral contra a Pólio', null, null, 'Poliomielite (paralisia Infantil)', 'INFANT', null, '2020-12-04 17:27:19.486000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (22, 'DOSAGE_REINFORCEMENT', 15, null, 15, 'Vacina contra Meningococo C', null, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', null, '2020-12-04 17:27:02.391000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (23, 'DOSAGE_1_REINFORCEMENT', 15, null, 15, 'Vacina Tríplice Bacteriana', 33, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', 24, '2020-12-04 17:27:09.448000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (24, 'DOSAGE_2_REINFORCEMENT', 72, null, 48, 'Vacina Tríplice Bacteriana', null, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', null, '2020-12-04 17:26:15.144000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (25, 'DOSAGE_REINFORCEMENT', 72, null, 48, 'Tríplice Viral', null, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', null, '2020-12-04 17:26:24.122000', 'A');




-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (138, 'DOSAGE_UNIQUE', 0, null, 0, 'BCG-ID', null, null, 'Formas graves de Tuberculose', 'INFANT', null, '2020-12-04 17:28:36.361000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (141, 'DOSAGE_1', 0, null, 0, 'Vacina contra Hepatite B', 1, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 142, '2020-12-04 17:28:34.525000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (142, 'DOSAGE_2', 1, null, 1, 'Vacina contra Hepatite B', 5, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 143, '2020-12-04 17:28:33.728000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (153, 'DOSAGE_1', 2, null, 2, 'Vacina Oral contra Rotavírus Humano', 2, 'É possível administar a primeira dose da Vacina Oral de Rotavírus Humano a partir de 1 mês e 15 dias a 3 meses e 7 dias de idade (6 a 14 semanas de vida)', 'Doenças diarréicas por Rotavírus', 'INFANT', 154, '2020-12-04 17:28:33.024000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (148, 'DOSAGE_1', 2, null, 2, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 149, '2020-12-04 17:28:32.243000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (144, 'DOSAGE_1', 2, null, 2, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 145, '2020-12-04 17:28:31.458000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (155, 'DOSAGE_1', 2, null, 2, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 156, '2020-12-04 17:28:30.607000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (159, 'DOSAGE_1', 3, null, 3, 'Vacina contra Meningococo C', 2, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 160, '2020-12-04 17:28:29.793000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (154, 'DOSAGE_2', 4, null, 4, 'Vacina Oral contra Rotavírus Humano', null, 'É possível administrar a segunda dose da Vacina Oral de Rotavírus Humano a partir de 3 meses e 7 dias a 5 meses e 15 dias de idade (14 a 24 semanas de ida). O intervalo mínimo reconizado entre a primeira e a segunda dose é de 4 semanas.', 'Doenças diarréicas por Rotavírus', 'INFANT', null, '2020-12-04 17:28:28.952000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (145, 'DOSAGE_2', 4, null, 4, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 146, '2020-12-04 17:28:28.088000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (156, 'DOSAGE_2', 4, null, 4, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 157, '2020-12-04 17:28:27.262000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (149, 'DOSAGE_2', 4, null, 4, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 150, '2020-12-04 17:28:26.400000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (160, 'DOSAGE_2', 5, null, 5, 'Vacina contra Meningococo C', 10, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 161, '2020-12-04 17:28:24.577000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (143, 'DOSAGE_2', 6, null, 6, 'Vacina contra Hepatite B', null, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', null, '2020-12-04 17:28:16.011000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (146, 'DOSAGE_3', 6, null, 6, 'Vacina Oral contra a Pólio', 9, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 147, '2020-12-04 17:28:04.691000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (150, 'DOSAGE_3', 6, null, 6, 'Vacina Tetravalente', 9, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 151, '2020-12-04 17:27:56.488000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (157, 'DOSAGE_3', 6, null, 6, 'Vacina contra Pneumococos', 6, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 158, '2020-12-04 17:27:49.335000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (164, 'DOSAGE_1', 9, null, 9, 'Vacina contra febre amarela', null, 'A vacina contra febre amarela está indicada para crianças a partir dos 09 meses de idade e quando for viajar para o exterior, vacinar contra Febre Amarela 10 (dez) dias antes da viagem.', 'Febre amarela', 'INFANT', null, '2020-12-04 17:27:40.540000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (158, 'DOSAGE_REINFORCEMENT', 12, null, 12, 'Vacina contra Pneumococos', null, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', null, '2020-12-04 17:27:32.960000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (162, 'DOSAGE_1', 12, null, 12, 'Tríplice Viral', 36, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', 163, '2020-12-04 17:27:26.286000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (147, 'DOSAGE_REINFORCEMENT', 15, null, 15, 'Vacina Oral contra a Pólio', null, null, 'Poliomielite (paralisia Infantil)', 'INFANT', null, '2020-12-04 17:27:19.486000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (151, 'DOSAGE_1_REINFORCEMENT', 15, null, 15, 'Vacina Tríplice Bacteriana', 33, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', 152, '2020-12-04 17:27:09.448000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (161, 'DOSAGE_REINFORCEMENT', 15, null, 15, 'Vacina contra Meningococo C', null, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', null, '2020-12-04 17:27:02.391000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (163, 'DOSAGE_REINFORCEMENT', 72, null, 48, 'Tríplice Viral', null, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', null, '2020-12-04 17:26:24.122000', 'A');
-- INSERT INTO vaccine (id, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, create_at, situation) VALUES (152, 'DOSAGE_2_REINFORCEMENT', 72, null, 48, 'Vacina Tríplice Bacteriana', null, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', null, '2020-12-04 17:26:15.144000', 'A');



-- create table if not exists vaccine
-- (
--     id bigint not null
--         constraint vaccine_pkey
--             primary key,
--     create_at timestamp,
--     situation char,
--     dosage varchar(255),
--     final_range integer,
--     frequency integer,
--     initial_range integer,
--     name varchar(255),
--     next_dosage integer,
--     observation varchar(500),
--     prevented_diseases varchar(255),
--     range varchar(20) not null,
--     next_vaccine_id bigint
--         constraint fk3wyt8t5a41vbg3370mg8diihs
--             references vaccine,
--     requirement varchar(16) default 'MANDATORY'::character varying
-- );
--
--
--
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (26, '2020-12-10 23:58:41.863885', 'A', 'DOSAGE_REINFORCEMENT', 72, null, 0, 'Vacina contra febre amarela', null, 'Antes de viajar para o exterior, vacinar contra Febre Amarela 10 (dez) dias antes da viagem.', 'Febre amarela', 'INFANT', null, 'TRAVEL');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (25, '2020-12-04 17:26:24.122000', 'A', 'DOSAGE_REINFORCEMENT', 72, null, 48, 'Tríplice Viral', null, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (24, '2020-12-04 17:26:15.144000', 'A', 'DOSAGE_2_REINFORCEMENT', 72, null, 48, 'Vacina Tríplice Bacteriana', null, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (23, '2020-12-04 17:27:09.448000', 'A', 'DOSAGE_1_REINFORCEMENT', 15, null, 15, 'Vacina Tríplice Bacteriana', 33, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', 24, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (22, '2020-12-04 17:27:02.391000', 'A', 'DOSAGE_REINFORCEMENT', 15, null, 15, 'Vacina contra Meningococo C', null, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (21, '2020-12-04 17:27:19.486000', 'A', 'DOSAGE_REINFORCEMENT', 15, null, 15, 'Vacina Oral contra a Pólio', null, null, 'Poliomielite (paralisia Infantil)', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (20, '2020-12-04 17:27:32.960000', 'A', 'DOSAGE_REINFORCEMENT', 12, null, 12, 'Vacina contra Pneumococos', null, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (19, '2020-12-04 17:27:26.286000', 'A', 'DOSAGE_1', 12, null, 12, 'Tríplice Viral', 36, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', 25, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (18, '2020-12-04 17:27:40.540000', 'A', 'DOSAGE_1', 9, null, 9, 'Vacina contra febre amarela', null, 'A vacina contra febre amarela está indicada para crianças a partir dos 09 meses de idade e quando for viajar para o exterior, vacinar contra Febre Amarela 10 (dez) dias antes da viagem.', 'Febre amarela', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (17, '2020-12-04 17:28:16.011000', 'A', 'DOSAGE_2', 6, null, 6, 'Vacina contra Hepatite B', null, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (16, '2020-12-04 17:28:04.691000', 'A', 'DOSAGE_3', 6, null, 6, 'Vacina Oral contra a Pólio', 9, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 21, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (15, '2020-12-04 17:27:49.335000', 'A', 'DOSAGE_3', 6, null, 6, 'Vacina contra Pneumococos', 6, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 20, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (14, '2020-12-04 17:27:56.488000', 'A', 'DOSAGE_3', 6, null, 6, 'Vacina Tetravalente', 9, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 23, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (13, '2020-12-04 17:28:24.577000', 'A', 'DOSAGE_2', 5, null, 5, 'Vacina contra Meningococo C', 10, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 22, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (12, '2020-12-04 17:28:27.262000', 'A', 'DOSAGE_2', 4, null, 4, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 15, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (11, '2020-12-04 17:28:28.952000', 'A', 'DOSAGE_2', 4, null, 4, 'Vacina Oral contra Rotavírus Humano', null, 'É possível administrar a segunda dose da Vacina Oral de Rotavírus Humano a partir de 3 meses e 7 dias a 5 meses e 15 dias de idade (14 a 24 semanas de ida). O intervalo mínimo reconizado entre a primeira e a segunda dose é de 4 semanas.', 'Doenças diarréicas por Rotavírus', 'INFANT', null, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (10, '2020-12-04 17:28:28.088000', 'A', 'DOSAGE_2', 4, null, 4, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 16, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (9, '2020-12-04 17:28:26.400000', 'A', 'DOSAGE_2', 4, null, 4, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 14, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (8, '2020-12-04 17:28:29.793000', 'A', 'DOSAGE_1', 3, null, 3, 'Vacina contra Meningococo C', 2, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 13, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (7, '2020-12-04 17:28:31.458000', 'A', 'DOSAGE_1', 2, null, 2, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 10, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (6, '2020-12-04 17:28:30.607000', 'A', 'DOSAGE_1', 2, null, 2, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 12, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (5, '2020-12-04 17:28:33.024000', 'A', 'DOSAGE_1', 2, null, 2, 'Vacina Oral contra Rotavírus Humano', 2, 'É possível administar a primeira dose da Vacina Oral de Rotavírus Humano a partir de 1 mês e 15 dias a 3 meses e 7 dias de idade (6 a 14 semanas de vida)', 'Doenças diarréicas por Rotavírus', 'INFANT', 11, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (4, '2020-12-04 17:28:32.243000', 'A', 'DOSAGE_1', 2, null, 2, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 9, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (3, '2020-12-04 17:28:33.728000', 'A', 'DOSAGE_2', 1, null, 1, 'Vacina contra Hepatite B', 5, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 17, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (2, '2020-12-04 17:28:34.525000', 'A', 'DOSAGE_1', 0, null, 0, 'Vacina contra Hepatite B', 1, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 3, 'MANDATORY');
-- INSERT INTO vaccine (id, create_at, situation, dosage, final_range, frequency, initial_range, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (1, '2020-12-04 17:28:36.361000', 'A', 'DOSAGE_UNIQUE', 0, null, 0, 'BCG-ID', null, null, 'Formas graves de Tuberculose', 'INFANT', null, 'MANDATORY');
--


INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (57, '2020-12-14 09:38:11.779000', 'A', 'DOSAGE_DECADE', 1320, 1440, null, 'Dupla Bacteriana Adulto (dT)', null, null, 'Difteriano e Tétano', 'ADULT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (56, '2020-12-14 09:38:07.773000', 'A', 'DOSAGE_DECADE', 1200, 1320, null, 'Dupla Bacteriana Adulto (dT)', null, null, 'Difteriano e Tétano', 'ADULT', 57, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (55, '2020-12-14 09:38:03.788000', 'A', 'DOSAGE_DECADE', 1080, 1200, null, 'Dupla Bacteriana Adulto (dT)', null, null, 'Difteriano e Tétano', 'ADULT', 56, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (54, '2020-12-14 09:37:59.056000', 'A', 'DOSAGE_DECADE', 960, 1080, null, 'Dupla Bacteriana Adulto (dT)', null, null, 'Difteriano e Tétano', 'ADULT', 55, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (53, '2020-12-14 09:37:55.498000', 'A', 'DOSAGE_DECADE', 840, 960, null, 'Dupla Bacteriana Adulto (dT)', null, null, 'Difteriano e Tétano', 'ADULT', 54, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (52, '2020-12-14 09:37:51.493000', 'A', 'DOSAGE_DECADE', 720, 840, null, 'Dupla Bacteriana Adulto (dT)', null, null, 'Difteriano e Tétano', 'ADULT', 53, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (51, '2020-12-14 09:30:29.713000', 'A', 'DOSAGE_3', 720, 1440, null, 'Hepatite B', null, '', 'Hepatite B', 'ADULT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (50, '2020-12-14 09:30:23.569000', 'A', 'DOSAGE_2', 720, 1440, null, 'Hepatite B', 6, '', 'Hepatite B', 'ADULT', 51, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (49, '2020-12-14 09:30:17.218000', 'A', 'DOSAGE_1', 720, 1440, null, 'Hepatite B', 1, '', 'Hepatite B', 'ADULT', 50, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (48, '2020-12-14 09:30:10.538000', 'A', 'DOSAGE_UNIQUE', 348, 708, null, 'Tríplice Viral', null, '', 'Sarampo, Rubéola e Caxumba', 'ADULT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (47, '2020-12-14 09:23:18.080000', 'A', 'DOSAGE_UNIQUE', 240, 708, null, 'Vacina contra Febre Amarela', null, '', 'Febre Amarela', 'ADULT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (46, '2020-12-14 09:20:37.970000', 'A', 'DOSAGE_DECADE', 600, 708, null, 'Dupla Bacteriana Adulto (dT)', null, '', 'Difteriano e Tétano', 'ADULT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (45, '2020-12-14 09:20:33.824000', 'A', 'DOSAGE_DECADE', 480, 600, null, 'Dupla Bacteriana Adulto (dT)', null, '', 'Difteriano e Tétano', 'ADULT', 46, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (44, '2020-12-14 09:19:10.172000', 'A', 'DOSAGE_DECADE', 360, 480, null, 'Dupla Bacteriana Adulto (dT)', null, '', 'Difteriano e Tétano', 'ADULT', 45, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (43, '2020-12-14 09:16:23.555000', 'A', 'DOSAGE_DECADE', 240, 360, null, 'Dupla Bacteriana Adulto (dT)', null, '', 'Difteriano e Tétano', 'ADULT', 44, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (42, '2020-12-14 09:13:51.594000', 'A', 'DOSAGE_3', 240, 708, null, 'Hepatite B', null, '', 'Hepatite B', 'ADULT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (41, '2020-12-14 09:13:45.755000', 'A', 'DOSAGE_2', 240, 708, null, 'Hepatite B', 6, '', 'Hepatite B', 'ADULT', 42, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (40, '2020-12-14 09:13:41.050000', 'A', 'DOSAGE_1', 240, 708, null, 'Hepatite B', 1, '', 'Hepatite B', 'ADULT', 41, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (39, '2020-12-14 09:09:25.809000', 'A', 'DOSAGE_2', 240, 348, null, 'Tríplice Viral', null, '', 'Sarampo, Rubéola e Caxumba', 'ADULT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (38, '2020-12-14 09:09:20.767000', 'A', 'DOSAGE_1', 240, 348, null, 'Tríplice Viral', 36, '', 'Sarampo, Rubéola e Caxumba', 'ADULT', 39, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (37, '2020-12-14 09:07:00.672000', 'A', 'DOSAGE_UNIQUE', 132, 228, null, 'Vacina contra Febre Amarela', null, '', 'Febre Amarela', 'TEENAGER', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (36, '2020-12-14 09:06:10.954000', 'A', 'DOSAGE_2', 132, 228, null, 'Tríplice Viral', null, '', 'Sarampo, Rubéola e Caxumba', 'TEENAGER', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (35, '2020-12-14 08:57:17.889000', 'A', 'DOSAGE_1', 132, 228, null, 'Tríplice Viral', 36, '', 'Sarampo, Rubéola e Caxumba', 'TEENAGER', 36, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (34, '2020-12-14 08:53:03.121000', 'A', 'DOSAGE_DECADE', 132, 228, null, 'Dupla Bacteriana Adulto (dT)', null, '', 'Difteriano e Tétano', 'TEENAGER', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (33, '2020-12-14 08:52:55.383000', 'A', 'DOSAGE_3', 132, 228, null, 'Hepatite B', null, '', 'Hepatite B', 'TEENAGER', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (32, '2020-12-14 08:52:47.967000', 'A', 'DOSAGE_2', 132, 228, null, 'Hepatite B', 6, '', 'Hepatite B', 'TEENAGER', 33, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (31, '2020-12-14 08:52:41.241000', 'A', 'DOSAGE_1', 132, 228, null, 'Hepatite B', 1, '', 'Hepatite B', 'TEENAGER', 32, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (30, '2020-12-13 07:16:07.027000', 'A', 'DOSAGE_UNIQUE', 132, 144, null, 'Meningocócia ACWY', null, '', 'Meningocócia (causadas por bactérias do grupo A, C, W e Y)', 'TEENAGER', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (29, '2020-12-13 07:14:50.779000', 'A', 'DOSAGE_2', 132, 168, null, 'Papilomavírus Humano (HPV)', null, 'Segunda dose após 6 meses da aplicação da peimeira dose.', 'Cânceres e verrugas genitais', 'TEENAGER', null, 'BOY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (28, '2020-12-13 07:13:39.528000', 'A', 'DOSAGE_1', 132, 168, null, 'Papilomavírus Humano (HPV)', 6, 'Duas doses com intervalo de 6 meses.', 'Cânceres e verrugas genitais', 'TEENAGER', 29, 'BOY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (27, '2020-12-13 07:11:37.139000', 'A', 'DOSAGE_2', 108, 180, null, 'Papilomavírus Humano (HPV)', null, '', 'Cânceres e verrugas genitais', 'INFANT', null, 'GIRL');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (26, '2020-12-13 07:06:31.321000', 'A', 'DOSAGE_1', 108, 180, null, 'Papilomavírus Humano (HPV)', 6, '', 'Cânceres e verrugas genitais', 'INFANT', 27, 'GIRL');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (25, '2020-12-04 17:26:24.122000', 'A', 'DOSAGE_REINFORCEMENT', 48, 72, null, 'Tríplice Viral', null, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (24, '2020-12-04 17:26:15.144000', 'A', 'DOSAGE_2_REINFORCEMENT', 48, 72, null, 'Vacina Tríplice Bacteriana', null, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (23, '2020-12-04 17:27:09.448000', 'A', 'DOSAGE_1_REINFORCEMENT', 15, 15, null, 'Vacina Tríplice Bacteriana', 33, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche', 'INFANT', 24, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (22, '2020-12-04 17:27:02.391000', 'A', 'DOSAGE_REINFORCEMENT', 15, 15, null, 'Vacina contra Meningococo C', null, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (21, '2020-12-04 17:27:19.486000', 'A', 'DOSAGE_REINFORCEMENT', 15, 15, null, 'Vacina Oral contra a Pólio', null, null, 'Poliomielite (paralisia Infantil)', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (20, '2020-12-04 17:27:32.960000', 'A', 'DOSAGE_REINFORCEMENT', 12, 12, null, 'Vacina contra Pneumococos', null, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (19, '2020-12-04 17:27:26.286000', 'A', 'DOSAGE_1', 12, 12, null, 'Tríplice Viral', 36, null, 'Sarampo, Rubéola e Caxumba', 'INFANT', 25, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (18, '2020-12-04 17:27:40.540000', 'A', 'DOSAGE_1', 9, 9, null, 'Vacina contra Febre Amarela', null, 'A vacina contra febre amarela está indicada para crianças a partir dos 09 meses de idade e quando for viajar para o exterior, vacinar contra Febre Amarela 10 (dez) dias antes da viagem.', 'Febre Amarela', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (17, '2020-12-04 17:28:16.011000', 'A', 'DOSAGE_3', 6, 6, null, 'Vacina contra Hepatite B', null, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (16, '2020-12-04 17:28:04.691000', 'A', 'DOSAGE_3', 6, 6, null, 'Vacina Oral contra a Pólio', 9, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 21, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (15, '2020-12-04 17:27:49.335000', 'A', 'DOSAGE_3', 6, 6, null, 'Vacina contra Pneumococos', 6, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 20, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (14, '2020-12-04 17:27:56.488000', 'A', 'DOSAGE_3', 6, 6, null, 'Vacina Tetravalente', 9, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 23, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (13, '2020-12-04 17:28:24.577000', 'A', 'DOSAGE_2', 5, 5, null, 'Vacina contra Meningococo C', 10, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 22, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (12, '2020-12-04 17:28:27.262000', 'A', 'DOSAGE_2', 4, 4, null, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 15, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (11, '2020-12-04 17:28:28.952000', 'A', 'DOSAGE_2', 4, 4, null, 'Vacina Oral contra Rotavírus Humano', null, 'É possível administrar a segunda dose da Vacina Oral de Rotavírus Humano a partir de 3 meses e 7 dias a 5 meses e 15 dias de idade (14 a 24 semanas de ida). O intervalo mínimo reconizado entre a primeira e a segunda dose é de 4 semanas.', 'Doenças diarréicas por Rotavírus', 'INFANT', null, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (10, '2020-12-04 17:28:28.088000', 'A', 'DOSAGE_2', 4, 4, null, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 16, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (9, '2020-12-04 17:28:26.400000', 'A', 'DOSAGE_2', 4, 4, null, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 14, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (8, '2020-12-04 17:28:29.793000', 'A', 'DOSAGE_1', 3, 3, null, 'Vacina contra Meningococo C', 2, null, 'Doença invasiva causada por Neisseria meningitidis do Sorogrupo C', 'INFANT', 13, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (7, '2020-12-04 17:28:31.458000', 'A', 'DOSAGE_1', 2, 2, null, 'Vacina Oral contra a Pólio', 2, null, 'Poliomielite (paralisia Infantil)', 'INFANT', 10, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (6, '2020-12-04 17:28:30.607000', 'A', 'DOSAGE_1', 2, 2, null, 'Vacina contra Pneumococos', 2, null, 'Otite, sinusite, pneumonias, meningite causadas pelo Streptococcus pneumoniae', 'INFANT', 12, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (5, '2020-12-04 17:28:33.024000', 'A', 'DOSAGE_1', 2, 2, null, 'Vacina Oral contra Rotavírus Humano', 2, 'É possível administar a primeira dose da Vacina Oral de Rotavírus Humano a partir de 1 mês e 15 dias a 3 meses e 7 dias de idade (6 a 14 semanas de vida)', 'Doenças diarréicas por Rotavírus', 'INFANT', 11, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (4, '2020-12-04 17:28:32.243000', 'A', 'DOSAGE_1', 2, 2, null, 'Vacina Tetravalente', 2, 'O esquema de vacinação atual é feito aos 2, 4 e 6 meses de idade com a vacina Tetravalente e dois reforços com a Tríplice Bacteriana (DTP). O primeiro reforço aos 15 meses e o segundo reforço entre 4 e 6 anos.', 'Difteria, tétano, coqueluche, meningite e outras infecções por Haemophilus influenzae tipo B', 'INFANT', 9, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (3, '2020-12-04 17:28:33.728000', 'A', 'DOSAGE_2', 1, 1, null, 'Vacina contra Hepatite B', 5, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 17, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (2, '2020-12-04 17:28:34.525000', 'A', 'DOSAGE_1', 0, 0, null, 'Vacina contra Hepatite B', 1, 'A primeira dose da vacina contra a hepatite B deve ser administrada na maternidade, nas primeiras 12 horas de vida do recém-nascido. O esquema básico se constitui de 03 (três) doses, com intervalos de 30 dias da primeira para a segunda dose e 180 dias da primeira para a terceira dose.', 'Hepatite B', 'INFANT', 3, 'MANDATORY');
INSERT INTO vaccine (id, create_at, situation, dosage, initial_range, final_range, frequency, name, next_dosage, observation, prevented_diseases, range, next_vaccine_id, requirement) VALUES (1, '2020-12-04 17:28:36.361000', 'A', 'DOSAGE_UNIQUE', 0, 0, null, 'BCG-ID', null, null, 'Formas graves de Tuberculose', 'INFANT', null, 'MANDATORY');