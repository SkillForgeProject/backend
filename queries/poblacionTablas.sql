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
(3, 1, NOW() - INTERVAL 5 DAY, MD5(CONCAT('3','1',NOW())));
(3, 1, NOW() - INTERVAL 5 DAY, MD5(CONCAT('3','1',NOW())));
(3, 1, NOW() - INTERVAL 5 DAY, MD5(CONCAT('3','1',NOW())));

-- BADGES
INSERT INTO Badge (nombre, criterio, icono, id_usuario)
VALUES
('Aprendiz Activo', 'Completó el 50% de un curso', 'icon_medalla1.png', 3),
('Colaborador', 'Participó en varias evaluaciones', 'icon_medalla2.png', 4),
('Inicio Prometedor', 'Comenzó su primer curso', 'icon_medalla3.png', 6);

INSERT INTO cursos_puntuacion (cursoId, usuarioId, puntuacion)
VALUES
(1, 3, 3),
(1, 6, 4),
(2, 4, 5),
(2, 3, 3),
(2, 5,2),
(3, 5, 1),
(3, 4, 4),
(3, 6, 3);

-- Evaluación 1: Quiz sobre variables
INSERT INTO Pregunta (id_evaluacion, pregunta) VALUES
(1, '¿Qué es una variable en programación?'),
(1, '¿Cuál es el tipo de dato para números enteros en Java?'),
(1, '¿Qué palabra reservada se usa para declarar variables en JavaScript?'),
(1, '¿Qué diferencia hay entre variable local y global?'),
(1, '¿Qué significa inicializar una variable?');

-- Evaluación 2: Ejercicios de condicionales
INSERT INTO Pregunta (id_evaluacion, pregunta) VALUES
(2, '¿Qué instrucción se usa para tomar decisiones en programación?'),
(2, 'Escriba un ejemplo de estructura if-else'),
(2, '¿Qué operador lógico se usa para “y” en la mayoría de lenguajes?'),
(2, '¿Cómo funciona la sentencia switch?'),
(2, '¿Qué pasa si la condición de un if nunca se cumple?');

-- Evaluación 3: Mini proyecto regresión
INSERT INTO Pregunta (id_evaluacion, pregunta) VALUES
(3, '¿Qué es la regresión lineal?'),
(3, '¿Qué significa la variable dependiente en un modelo de regresión?'),
(3, '¿Qué método se usa para calcular los coeficientes de una recta de regresión?'),
(3, '¿Qué representa el coeficiente de determinación R^2?'),
(3, '¿Cómo se interpreta el valor del intercepto en un modelo de regresión?');

-- Evaluación 5: HTML estructurado
INSERT INTO Pregunta (id_evaluacion, pregunta) VALUES
(5, '¿Qué etiqueta se usa para definir un encabezado principal en HTML?'),
(5, '¿Qué diferencia hay entre <div> y <span>?'),
(5, '¿Qué atributo de una etiqueta <img> define la ruta de la imagen?'),
(5, '¿Qué etiqueta se usa para crear una lista ordenada?'),
(5, '¿Qué etiqueta representa un párrafo de texto?');

-- Evaluación 7: Async/Await práctico
INSERT INTO Pregunta (id_evaluacion, pregunta) VALUES
(7, '¿Qué es async en JavaScript?'),
(7, '¿Qué devuelve una función async por defecto?'),
(7, '¿Qué palabra reservada se usa para esperar la resolución de una promesa?'),
(7, '¿Qué pasa si no se maneja un error dentro de async/await?'),
(7, '¿Cómo se combina async/await con try...catch?');

-- Para Preguntas de la Evaluación 1 (ejemplo con IDs 1–5)
INSERT INTO Opcion_Pregunta (id_pregunta, opcion, esCorrecto) VALUES
(1, 'Una forma de almacenar datos en memoria', TRUE),
(1, 'Un bucle de ejecución', FALSE),
(1, 'Una clase de objeto', FALSE),
(1, 'Un archivo en disco', FALSE),

(2, 'int', TRUE),
(2, 'string', FALSE),
(2, 'boolean', FALSE),
(2, 'float32', FALSE),

(3, 'var', TRUE),
(3, 'for', FALSE),
(3, 'while', FALSE),
(3, 'function', FALSE),

(4, 'La visibilidad y el alcance', TRUE),
(4, 'El tipo de dato', FALSE),
(4, 'El tamaño en memoria', FALSE),
(4, 'Nada, son iguales', FALSE),

(5, 'Asignarle un valor inicial', TRUE),
(5, 'Declararla sin nombre', FALSE),
(5, 'Eliminarla del código', FALSE),
(5, 'Convertirla a cadena', FALSE);

-- Para Preguntas de la Evaluación 2 (IDs 6–10)
INSERT INTO Opcion_Pregunta (id_pregunta, opcion, esCorrecto) VALUES
(6, 'if', TRUE),
(6, 'for', FALSE),
(6, 'var', FALSE),
(6, 'print', FALSE),

(7, 'if (x > 0) { ... } else { ... }', TRUE),
(7, 'for (i=0; i<10; i++) { ... }', FALSE),
(7, 'int x;', FALSE),
(7, 'return;', FALSE);