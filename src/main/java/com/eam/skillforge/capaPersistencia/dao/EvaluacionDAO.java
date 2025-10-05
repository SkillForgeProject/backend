package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.CreacionEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.EvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.PreguntaDto;
import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import com.eam.skillforge.capaPersistencia.entidad.OpcionPregunta;
import com.eam.skillforge.capaPersistencia.entidad.Pregunta;
import com.eam.skillforge.capaPersistencia.mapper.EvaluacionMapper;
import com.eam.skillforge.capaPersistencia.mapper.OpcionPreguntaMapper;
import com.eam.skillforge.capaPersistencia.repositorio.EvaluacionRepositorio;
import com.eam.skillforge.capaPersistencia.repositorio.OpcionPreguntaRepositorio;
import  com.eam.skillforge.capaNegocio.mapper.PreguntaMapper;
import com.eam.skillforge.capaPersistencia.repositorio.PreguntaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EvaluacionDAO {

    private final OpcionPreguntaRepositorio opcionPreguntaRepositorio;
    private final EvaluacionMapper evaluacionMapper;
    private final PreguntaMapper preguntaMapper;
    private final OpcionPreguntaMapper opcionPreguntaMapper;
    private final EvaluacionRepositorio evaluacionRepositorio;
    private final PreguntaRepositorio preguntaRepositorio;

    public Optional<EvaluacionDto> buscarPorId(Long id) {
        return evaluacionRepositorio.findById(id)
                .map(evaluacionMapper::toDto);
    }

    public void crearEvaluacion(CreacionEvaluacionDto creacionEvaluacion) {
        Evaluacion evaluacion = evaluacionMapper.toEntidad(creacionEvaluacion.getEvaluacion());
        List<OpcionPregunta> opciones = opcionPreguntaMapper.toEntidadList(creacionEvaluacion.getOpciones());
        List<Pregunta> preguntas = preguntaMapper.toEntidadList( creacionEvaluacion.getPreguntas());

        Long id_evaluacion = insertarEvaluaciones(evaluacion);
        for( Pregunta pregunta :preguntas) {
            Long id_pregunta = insertarPreguntas(pregunta, id_evaluacion);
            for(OpcionPregunta opcionPregunta : opciones) {
                insertarOpcionesPregunta(opcionPregunta, id_pregunta);
            }
        }

    }

    private Long insertarEvaluaciones(Evaluacion evaluacion ) {
        evaluacionRepositorio.insertarEvaluacionNativa(evaluacion.getTitulo(), evaluacion.getModulo().getId(), evaluacion.getTipoEvaluacion().getId(), evaluacion.getPuntajeMax());
        return evaluacionRepositorio.obtenerUltimoIdInsertado();
    }

    private Long insertarPreguntas(Pregunta pregunta, Long id_evaluacion) {
        preguntaRepositorio.insertarPreguntaNativa(pregunta.getPregunta(), id_evaluacion);
        return preguntaRepositorio.obtenerUltimoIdInsertado();
    }

    private void insertarOpcionesPregunta(OpcionPregunta opciones, Long id_pregunta ) {
        opcionPreguntaRepositorio.insertarOpcionPreguntaNativa(id_pregunta, opciones.getOpcion(), opciones.getEsCorrecto());
    }

//    private Long obtenerUltimoIdEvaluacion() {
//        // Implementación específica para obtener el último ID de evaluación
//        // Aquí puedes usar una query específica o el método que prefieras
//        return evaluacionRepositorio.findTopByOrderByIdDesc().getId();
//    }

//    private int calcularOpcionesPorPregunta(List<OpcionPreguntaDto> opciones, int preguntaIndex, int totalPreguntas) {
//        int opcionesPorPregunta = opciones.size() / totalPreguntas;
//        int opcionesRestantes = opciones.size() % totalPreguntas;
//
//        return preguntaIndex < opcionesRestantes ? opcionesPorPregunta + 1 : opcionesPorPregunta;
//    }
}