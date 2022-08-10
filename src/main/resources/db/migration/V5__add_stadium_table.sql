CREATE SEQUENCE fc_stadium_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE fc_stadium
(
    id   int8 not null default nextval('fc_stadium_id_seq'),
    name VARCHAR(255),
    capacity int8,
    PRIMARY KEY (id)
);

INSERT INTO fc_stadium (name, capacity)
VALUES ('Metalurg', 500),
       ('Lujniki', 81000);