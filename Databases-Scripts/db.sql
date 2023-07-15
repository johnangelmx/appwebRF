CREATE
    DATABASE IF NOT EXISTS royalty_db;
USE
    royalty_db;

CREATE TABLE IF NOT EXISTS paquete
(
    id_paquete        INT          NOT NULL AUTO_INCREMENT,
    nombre_paquete    VARCHAR(255) NOT NULL,
    tipo_vuelo        VARCHAR(20)  NOT NULL,
    descripcion       VARCHAR(255) NOT NULL,
    numero_personas   INT          NOT NULL,
    precio            FLOAT        NOT NULL,
    precio_individual FLOAT        NOT NULL,
    estatus           BOOL         NOT NULL,
    PRIMARY KEY (id_paquete)
);

CREATE TABLE IF NOT EXISTS pasajero
(
    id_pasajero INT          NOT NULL AUTO_INCREMENT,
    id_paquete  INT          NOT NULL,
    id_viaje    INT          NOT NULL,
    nombres     VARCHAR(255) NOT NULL,
    apellidos   VARCHAR(255) NOT NULL,
    correo      VARCHAR(255) NOT NULL,
    hora        TIME(0)      NOT NULL,
    pagado      BOOL         NOT NULL,
    dpi         VARCHAR(255) NOT NULL,
    peso        FLOAT        NOT NULL,
    fecha       DATE         NOT NULL,
    PRIMARY KEY (id_pasajero),
    FOREIGN KEY (id_paquete) REFERENCES paquete (id_paquete)
);

INSERT INTO paquete (nombre_paquete, tipo_vuelo, descripcion, numero_personas, precio, precio_individual, estatus)
VALUES ('Paquete Royalty', 'Vuelo redondo', 'Un viaje de lujo a cualquier destino que elijas', 2, 10000, 5000, true);

INSERT INTO pasajero (id_paquete, id_viaje, nombres, apellidos, correo, hora, pagado, dpi, peso, fecha)
VALUES (1, 1, 'Juan', 'Perez', 'juan.perez@gmail.com', '10:00', 1, '1234567890123', 75, '2023-07-15');

INSERT INTO pasajero (id_paquete, id_viaje, nombres, apellidos, correo, hora, pagado, dpi, peso, fecha)
VALUES (1, 2, 'Maria', 'Gonzalez', 'maria.gonzalez@gmail.com', '12:00', 1, '9876543210987', 65, '2023-07-16');

INSERT INTO paquete (nombre_paquete, tipo_vuelo, descripcion, numero_personas, precio, precio_individual, estatus)
VALUES ('Paquete Royalty Plus', 'Vuelo redondo',
        'Un viaje de lujo a cualquier destino que elijas, con una escala en un destino exótico', 3, 15000, 5000, true);

INSERT INTO pasajero (id_paquete, id_viaje, nombres, apellidos, correo, hora, pagado, dpi, peso, fecha)
VALUES (2, 1, 'Pedro', 'Lopez', 'pedro.lopez@gmail.com', '10:00', 1, '1234567890123', 85, '2023-07-17');

INSERT INTO pasajero (id_paquete, id_viaje, nombres, apellidos, correo, hora, pagado, dpi, peso, fecha)
VALUES (2, 2, 'Ana', 'Martinez', 'ana.martinez@gmail.com', '12:00', 1, '9876543210987', 75, '2023-07-18');

INSERT INTO paquete (nombre_paquete, tipo_vuelo, descripcion, numero_personas, precio, precio_individual, estatus)
VALUES ('Paquete Royalty Deluxe', 'Vuelo redondo',
        'Un viaje de lujo a cualquier destino que elijas, con una escala en un destino exótico y alojamiento en un hotel de lujo',
        4, 20000, 5000, true);

INSERT INTO pasajero (id_paquete, id_viaje, nombres, apellidos, correo, hora, pagado, dpi, peso, fecha)
VALUES (3, 1, 'Juan Carlos', 'Rodriguez', 'juan.carlos.rodriguez@gmail.com', '10:00', 1, '1234567890123', 95,
        '2023-07-19');

INSERT INTO pasajero (id_paquete, id_viaje, nombres, apellidos, correo, hora, pagado, dpi, peso, fecha)
VALUES (3, 2, 'Maria Teresa', 'Gonzalez', 'maria.teresa.gonzalez@gmail.com', '12:00', 1, '9876543210987', 85,
        '2023-07-20');

SELECT *
FROM paquete;
SELECT *
FROM pasajero;