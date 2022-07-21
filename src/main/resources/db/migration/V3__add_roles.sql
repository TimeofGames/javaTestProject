CREATE SEQUENCE fc_player_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 4
    CACHE 1;

CREATE TABLE fc_player_role
(
    id   int not null default nextval('fc_player_role_id_seq'),
    role VARCHAR(255),
    PRIMARY KEY (id)
);



INSERT INTO fc_player_role (role)
VALUES ('Goalkeeper'),
       ('Midfielder'),
       ('Defender'),
       ('Forward');

ALTER TABLE fc_player
    ADD role int default 1;