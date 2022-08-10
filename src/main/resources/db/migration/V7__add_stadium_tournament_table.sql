CREATE TABLE fc_stadium_tournament
(
    id_stadium   int8 ,
    id_tournament int8,
    PRIMARY KEY (id_stadium,id_tournament)
);

INSERT into fc_stadium_tournament (id_stadium,id_tournament)
VALUES (1,1),(2,1),(2,2);