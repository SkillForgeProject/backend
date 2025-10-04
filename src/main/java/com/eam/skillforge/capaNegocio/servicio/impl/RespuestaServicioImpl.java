package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.EnvioEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto;
import com.eam.skillforge.capaNegocio.dto.RespuestaDto;
import com.eam.skillforge.capaNegocio.servicio.EvaluacionServicio;
import com.eam.skillforge.capaNegocio.servicio.OpcionRespuestaServicio;
import com.eam.skillforge.capaNegocio.servicio.RespuestaServicio;
import com.eam.skillforge.capaNegocio.servicio.UsuarioServicio;
import com.eam.skillforge.capaPersistencia.dao.RespuestaDAO;
import com.eam.skillforge.capaPersistencia.mapper.RespuestaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RespuestaServicioImpl implements RespuestaServicio {

    private final RespuestaDAO respuestaDAO;
    private final EvaluacionServicio evaluacionServicio;
    private final UsuarioServicio usuarioServicio;
    private final OpcionRespuestaServicio opcionPreguntaServicio;


    private final RespuestaMapper respuestaMapper;

    @Override
    public RespuestaDto crearRespuesta(EnvioEvaluacionDto respuestas) {
        log.info("Creando respuesta: {}", respuestas.getRespuestasId());

        validarDataRespuestas(respuestas);

        RespuestaDto nuevaRespuesta = new RespuestaDto(
                respuestas.getEvaluacionId(),
                respuestas.getUsuarioId()
        );
        nuevaRespuesta.setFecha(LocalDateTime.now());
        nuevaRespuesta.setPuntuacion((float) calcularPuntuacion(respuestas));

        RespuestaDto respuestaGuardada = respuestaDAO.guardar(nuevaRespuesta);
        log.info("Repuesta guardada exitosamente con ID: {}", respuestaGuardada.getId());

        return respuestaGuardada;
    }

    private double calcularPuntuacion(EnvioEvaluacionDto respuestas) {

        List<RespuestaCorrectaDto> respuestasCorrectas = opcionPreguntaServicio.getRespuestasCorrectas(respuestas.getEvaluacionId());

        Map<Long, Long> mapaPreguntasCorrectas = respuestasCorrectas.stream()
                .collect(Collectors.toMap(
                        RespuestaCorrectaDto::getPreguntaId,
                        RespuestaCorrectaDto::getOpcionCorrectaId
                ));

        int correctas = 0;
        int total = respuestas.getRespuestasId().size();
        log.info("Mapa de respuestas correctas: {}", mapaPreguntasCorrectas);

        for(int i = 0; i < total; i++) {
            Long preguntaId = respuestas.getPreguntasId().get(i);
            Long opcionSeleccionada = respuestas.getRespuestasId().get(i);
            Long opcionCorrecta = mapaPreguntasCorrectas.get(preguntaId);

            log.info("Pregunta {}, correcta={}, seleccionada={}", preguntaId, opcionCorrecta, opcionSeleccionada);

            if(Objects.equals(opcionCorrecta, opcionSeleccionada)) {
                correctas++;
            }
        }

        return total > 0 ? (correctas * 100.0) / total : 0.0;
    }


    private void validarDataRespuestas(EnvioEvaluacionDto respuetas) {

        // Validaciones
        usuarioServicio.buscarPorId(respuetas.getUsuarioId());
        evaluacionServicio.buscarPorId(respuetas.getEvaluacionId());

        // Validar cada respuesta ????
        // TODO: Comprobrar las validaciones de cada respuesta
    }


}
