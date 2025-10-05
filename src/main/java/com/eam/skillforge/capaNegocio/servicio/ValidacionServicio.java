package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursosPuntuacionDto;
import com.eam.skillforge.capaNegocio.excepciones.DatosInvalidosExcepcion;
import com.eam.skillforge.capaNegocio.excepciones.PuntuacionInvalidaExcepcion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidacionServicio {

    public void validarPuntuacionCurso(CursosPuntuacionDto puntuacionDto) {
        log.debug("Validando puntuación de curso: {}", puntuacionDto);

        if (puntuacionDto == null) {
            throw new DatosInvalidosExcepcion("Los datos de puntuación no pueden ser nulos");
        }

        if (puntuacionDto.getCursoId() == null) {
            throw new DatosInvalidosExcepcion("cursoId", "null");
        }

        if (puntuacionDto.getUsuarioId() == null) {
            throw new DatosInvalidosExcepcion("usuarioId", "null");
        }

        if (puntuacionDto.getPuntuacion() == null) {
            throw new DatosInvalidosExcepcion("puntuacion", "null");
        }

        validarRangoPuntuacion(puntuacionDto.getPuntuacion());
    }

    public void validarRangoPuntuacion(Float puntuacion) {
        if (puntuacion == null) {
            throw new PuntuacionInvalidaExcepcion("La puntuación no puede ser nula");
        }

        if (puntuacion < 0.0f || puntuacion > 5.0f) {
            throw new PuntuacionInvalidaExcepcion(puntuacion);
        }

        log.debug("Puntuación válida: {}", puntuacion);
    }

    public void validarIdPositivo(Long id, String nombreCampo) {
        if (id == null) {
            throw new DatosInvalidosExcepcion(nombreCampo, "null");
        }

        if (id <= 0) {
            throw new DatosInvalidosExcepcion(nombreCampo, id + " (debe ser mayor que 0)");
        }

        log.debug("ID válido para {}: {}", nombreCampo, id);
    }
}