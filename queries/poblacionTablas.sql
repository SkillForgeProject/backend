-- USUARIOS
INSERT INTO Usuario (nombre, email, id_rol, id_departamento)
VALUES
('Carlos Ruiz', 'carlos.ruiz@sistemas.com', 1, 1),
('Ana Torres', 'ana.torres@biomedica.com', 2, 2),
('Luis Pérez', 'luis.perez@sistemas.com', 3, 1),
('Marta Gómez', 'marta.gomez@contabilidad.com', 3, 3),
('Sofía Ramírez', 'sofia.ramirez@biomedica.com', 3, 2),
('Diego López', 'diego.lopez@sistemas.com', 3, 1);

-- CURSOS
INSERT INTO Curso (titulo, descripcion, duracionEstim, nivel)
VALUES
('Introducción a la Programación', 'Curso base para aprender lógica y fundamentos', 20, 1),
('Bases de Datos Relacionales', 'Curso para aprender SQL y diseño de bases de datos', 25, 2),
('Mantenimiento de Equipos Biomédicos', 'Curso técnico especializado', 30, 3);

-- MÓDULOS
INSERT INTO Modulo (cursoId, titulo, tipo, orden)
VALUES
(1, 'Variables y Tipos de Datos', 1, '1'),
(1, 'Condicionales y Bucles', 1, '2'),
(1, 'Funciones y Procedimientos', 2, '3'),
(2, 'Modelado de Datos', 2, '1'),
(2, 'Consultas SQL Básicas', 1, '2'),
(2, 'Consultas Avanzadas', 1, '3'),
(3, 'Fundamentos de Electrónica', 2, '1'),
(3, 'Calibración de Equipos', 3, '2'),
(3, 'Protocolos de Seguridad', 2, '3');

-- INSCRIPCIONES
-- (usuarios 3-6 son estudiantes, progreso < 100)
INSERT INTO Inscripcion (usuarioId, cursoId, moduloId, progreso, fechaInscripcion, fechaUltimoEstado, estado)
VALUES
(3, 1, 1, 60, NOW() - INTERVAL 15 DAY, NOW() - INTERVAL 2 DAY, 3),
(3, 1, 2, 70, NOW() - INTERVAL 15 DAY, NOW() - INTERVAL 2 DAY, 3),
(4, 2, 4, 40, NOW() - INTERVAL 10 DAY, NOW() - INTERVAL 1 DAY, 3),
(4, 2, 5, 55, NOW() - INTERVAL 10 DAY, NOW() - INTERVAL 1 DAY, 3),
(5, 3, 7, 20, NOW() - INTERVAL 8 DAY, NOW() - INTERVAL 1 DAY, 3),
(5, 3, 8, 10, NOW() - INTERVAL 8 DAY, NOW() - INTERVAL 1 DAY, 2),
(6, 1, 1, 30, NOW() - INTERVAL 6 DAY, NOW() - INTERVAL 1 DAY, 3),
(6, 1, 2, 45, NOW() - INTERVAL 6 DAY, NOW() - INTERVAL 1 DAY, 3);

-- EVALUACIONES
INSERT INTO Evaluacion (moduloId, titulo, id_tipo_evaluacion, puntajeMax)
VALUES
(1, 'Quiz de Variables', 1, 10),
(2, 'Ejercicio de Bucles', 3, 20),
(4, 'Modelo Entidad-Relación', 2, 15),
(5, 'Examen de Consultas Básicas', 1, 25),
(7, 'Evaluación de Electrónica', 3, 20),
(8, 'Simulación de Calibración', 2, 30);

-- RESPUESTAS (algunas entregadas por usuarios)
INSERT INTO Respuesta (evaluacionId, usuarioId, puntuacion, fecha)
VALUES
(1, 3, 8, NOW() - INTERVAL 3 DAY),
(2, 3, 15, NOW() - INTERVAL 2 DAY),
(3, 4, 10, NOW() - INTERVAL 1 DAY),
(4, 4, 22, NOW() - INTERVAL 1 DAY),
(5, 5, 14, NOW() - INTERVAL 1 DAY),
(6, 5, 20, NOW() - INTERVAL 1 DAY);

-- CERTIFICADOS (solo uno completado en el pasado)
INSERT INTO Certificado (usuarioId, cursoId, fechaEmision, hash)
VALUES
(3, 1, NOW() - INTERVAL 5 DAY, MD5(CONCAT('3','1',NOW())));

-- BADGES
INSERT INTO Badge (nombre, criterio, icono, id_usuario)
VALUES
('Aprendiz Activo', 'Completó el 50% de un curso', 'icon_medalla1.png', 3),
('Colaborador', 'Participó en varias evaluaciones', 'icon_medalla2.png', 4),
('Inicio Prometedor', 'Comenzó su primer curso', 'icon_medalla3.png', 6);

INSERT INTO cursos_puntuacion (cursoId, usuarioId, puntuacion)
VALUES
(1, 3, 85.5),
(1, 6, 72.0),
(2, 4, 90.0),
(2, 3, 77.5),
(2, 5, 60.0),
(3, 5, 88.0),
(3, 4, 70.5),
(3, 6, 65.0);