CREATE TABLE USERS (
                       id bigint not null auto_increment,
                       username varchar(255),
                       password varchar(255),
                       primary key (id)
);

CREATE TABLE USERS_ROLES (user_id bigint not null, role varchar(255));

ALTER TABLE USERS_ROLES ADD constraint FK8m7edvc6gfyryjnssxluj5stv foreign key (user_id) references USERS(id);

insert into USERS (id, username, password) values (1, 'jacekrg', '$2a$09$DvYBLyQttXxe62o7iiuNP.C1aMuywOxmmQVPD9Zf2yrUWHFDJYvS6');

insert into USERS_ROLES (user_id, role) values (1, 'MANAGER');

insert into USERS (id, username, password) values (2, 'ali', '$2a$09$4PMd9VIsAXf7qrAIQMfsBOwXbE/N/U3sY0wp.75OgNIs5BMo4cL62');

insert into USERS_ROLES (user_id, role) values (2, 'RECEPTION');
