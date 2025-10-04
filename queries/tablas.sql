CREATE DATABASE skillforge;
USE skillforge;

CREATE TABLE departamento (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(255)
);

INSERT INTO departamento (nombre) VALUES ('SISTEMAS'), ('BIOMEDICA'), ('CONTABILIDAD');

CREATE TABLE rol (
  id integer PRIMARY KEY AUTO_INCREMENT,
  nombre varchar(255)
);

CREATE TABLE Usuario (
  id integer PRIMARY KEY AUTO_INCREMENT,
  nombre varchar(255),
  email varchar(255) UNIQUE,
  id_rol INT,
  id_departamento INT,
  FOREIGN KEY (id_rol) REFERENCES rol(id),
  FOREIGN KEY (id_departamento) REFERENCES departamento(id)
);


INSERT INTO rol (nombre) VALUES ('ADMIN'), ('INSTRUCT'), ('USER');

CREATE TABLE Curso (
  id integer PRIMARY KEY AUTO_INCREMENT,
  titulo varchar(255) UNIQUE,
  descripcion varchar(255),
  duracionEstim integer,
  nivel int
);

CREATE TABLE categoria (
  id int PRIMARY KEY AUTO_INCREMENT,
  nombre NVARCHAR(255)
);

INSERT INTO categoria (nombre) VALUES ('INDUCCION'), ('CAPACITACION'), ('ESPECIALIZACION');

CREATE TABLE Recurso (
  id int PRIMARY KEY AUTO_INCREMENT,
  nombre NVARCHAR(255)
);

INSERT INTO recurso (nombre) VALUES ('VIDEO'), ('TEXTO'), ('PRACTICA');

CREATE TABLE Modulo (
  id integer PRIMARY KEY AUTO_INCREMENT,
  cursoId integer,
  titulo varchar(255),
  tipo int,
  orden varchar(255),
  FOREIGN KEY (tipo) REFERENCES recurso(id),
  FOREIGN KEY (cursoId) REFERENCES Curso(id)
);


CREATE TABLE Estado (
  id int PRIMARY KEY AUTO_INCREMENT,
  etapa varchar(255)
);

INSERT INTO estado (etapa) VALUES ('NOINSCRITO'), ('INSCRITO'), ('ENPROGRESO'), ('COMPLETADO');

CREATE TABLE Inscripcion (
  id integer PRIMARY KEY AUTO_INCREMENT,
  usuarioId integer,
  cursoId integer,
  moduloId integer,
  progreso integer,
  fechaInscripcion DATETIME,
  fechaUltimoEstado TIMESTAMP,
  estado INT,
  FOREIGN KEY (usuarioId) REFERENCES Usuario(id),
  FOREIGN KEY (estado) REFERENCES Estado(id),
  FOREIGN KEY (moduloId) REFERENCES Modulo(id)
);


CREATE TABLE Tipo_Evaluacion (
  id integer PRIMARY KEY AUTO_INCREMENT,
  nombre NVARCHAR(255)
);

CREATE TABLE Evaluacion (
  id integer PRIMARY KEY AUTO_INCREMENT,
  moduloId integer,
  titulo varchar(255),
  id_tipo_evaluacion int,
  puntajeMax INTEGER,
  FOREIGN KEY (moduloId) REFERENCES Modulo(id),
  FOREIGN KEY (id_tipo_evaluacion) REFERENCES Tipo_Evaluacion(id)
);

INSERT INTO tipo_evaluacion (nombre) VALUES ('MCQ'), ('ABIERTA'), ('CERRADA');

CREATE TABLE Respuesta (
  id integer PRIMARY KEY AUTO_INCREMENT,
  evaluacionId integer,
  usuarioId integer,
  puntuacion integer,
  fecha TIMESTAMP,
  FOREIGN KEY (evaluacionId) REFERENCES Evaluacion(id),
  FOREIGN KEY (usuarioId) REFERENCES Usuario(id)
);

CREATE TABLE Certificado (
  id integer PRIMARY KEY AUTO_INCREMENT,
  usuarioId integer,
  cursoId integer,
  fechaEmision timestamp,
  hash varchar(255),
  FOREIGN KEY (usuarioId) REFERENCES Usuario(id),
  FOREIGN KEY (cursoId) REFERENCES Curso(id)
);

CREATE TABLE Badge (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    criterio VARCHAR(255),
    icono NVARCHAR(255),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);