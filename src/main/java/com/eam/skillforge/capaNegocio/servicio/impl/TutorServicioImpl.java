package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.*;
import com.eam.skillforge.capaNegocio.excepciones.UsuarioNoAutorizadoExcepcion;
import com.eam.skillforge.capaNegocio.servicio.*;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
import com.eam.skillforge.capaPersistencia.dao.ModuloDAO;
import com.eam.skillforge.capaPersistencia.dao.TutorDAO;
import com.eam.skillforge.capaPersistencia.entidad.Inscripcion;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TutorServicioImpl implements TutorServicio {

    private final TutorDAO tutorDAO;
    private final CursoDAO cursoDAO;
    private final ModuloDAO moduloDAO;
    private final CursoServicio cursoServicio;
    private final ModuloServicio moduloServicio;
    private final UsuarioServicio usuarioServicio;
    private final ModuloRecursoServicio moduloRecursoServicio;

    @Override
    public List<CursoDto> getCursosPorTutorId(Long tutorId) {
        log.debug("Buscando cursos por ID de tutor: {}", tutorId);

        getTutorPorId(tutorId);

        return tutorDAO.getCursosPorTutorId(tutorId);
    }

    @Override
    public Optional<UsuarioDto> getTutorPorId(Long tutorId) {
        log.debug("Buscando tutor por ID: {}", tutorId);

        if (tutorId != 2) {
            throw new UsuarioNoAutorizadoExcepcion("Este usuario no tiene permisos para utilizar este endpoint");
        }

        return tutorDAO.getTutorPorId(tutorId);
    }

    @Override
    public void eliminarCurso(Long cursoId) {
        log.debug("Eliminando curso ID: {}", cursoId);
        cursoServicio.getCursoPorId(cursoId);

        boolean eliminado = cursoDAO.eliminarPorId(cursoId);
        if (!eliminado) {
            throw new RuntimeException("Error al eliminar curso por ID: " + cursoId);
        }

        log.info("Curso eliminado satisfactoriamente ID: {}", cursoId);
    }

    @Override
    public CursoDto crearCurso(CursoDto curso) {

        validarDataCurso(curso);

        CursoDto cursoCreado = tutorDAO.crearCurso(curso);
        log.info("Curso creado satisfcatoriamente con ID: {}", cursoCreado.getId());
        return cursoCreado;
    }

    private void validarDataCurso(CursoDto curso) {
        //TODO: implementar validaciones
    }

    @Override
    public CursoDto actualizarCurso(Long cursoId, CursoDto curso) {
        log.info("Actualizando curso ID: {}", cursoId);

        CursoDto cursoActualizado = cursoServicio.actualizarCurso(cursoId, curso);
        log.info("Curso actualizado exitosamente ID: {}", cursoId);
        return cursoActualizado;
    }

    @Override
    public ModuloDto crearModulo(ModuloDto modulo) {
        validarDataModulo(modulo);

        ModuloDto moduloCreado = moduloServicio.guardarModulo(modulo);
        log.info("Módulo creado satisfactoriamente con ID: {}", moduloCreado.getId());
        return moduloCreado;
    }

    private void validarDataModulo(ModuloDto modulo) {
        //TODO: implementar validaciones
    }

    @Override
    public ModuloDto actualizarModulo(Long moduloId, ModuloDto modulo) {
        log.info("Actualiando módulo ID: {}", moduloId);

        moduloServicio.getModuloPorId(moduloId);
        validarDataModulo(modulo);

        ModuloDto moduloActualizado = moduloDAO.actualizar(moduloId, modulo)
                .orElseThrow(() -> new RuntimeException("Error al actualizar el módulo"));
        log.info("Módulo actualizado exitosamente ID: {}", moduloId);

        return moduloActualizado;
    }

    public boolean eliminarModuloPorId(Long id) {
        log.info("Iniciando eliminación de módulo con ID: {}", id);

        try {
            boolean resultado = moduloServicio.eliminarModuloPorId(id);
            log.info("Módulo con ID {} eliminado exitosamente", id);
            return resultado;
        } catch (Exception e) {
            log.error("Error al eliminar módulo con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    public void crearEvaluacion(CreacionEvaluacionDto creacionEvaluacion) {
        tutorDAO.crearEvaluacion(creacionEvaluacion);
    }

    @Override
    public InscripcionDto asignarCurso(Long usuarioId, Long cursoId) {
        log.info("Asignando curso al usuario con ID: {} : {}", usuarioId, cursoId);

        usuarioServicio.buscarPorId(usuarioId);
        cursoServicio.getCursoPorId(cursoId);

        List<ModuloDto> modulos = moduloServicio.getModulosPorCursoId(cursoId);
        List<InscripcionDto> inscripciones = new ArrayList<>();

        for(ModuloDto modulo : modulos) {
            InscripcionDto paraInscribir = new InscripcionDto(
                    null,
                    usuarioId,
                    cursoId,
                    modulo.getId(),
                    0.0f,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    1L
            );

            InscripcionDto inscrito = tutorDAO.inscribir(paraInscribir);
            inscripciones.add(inscrito);
            log.info("Inscrito {}", inscrito);
            log.info("Asignación de curso exitosa con ID: {} : {} : {}", usuarioId, cursoId, inscrito.getId());
        }


        return inscripciones.getLast();
    }

    @Override
    public ModuloRecursoDto cargarRecurso(Long moduloId, Long recursoId, MultipartFile archivo, String url) {
        try {
            ModuloRecursoDto recursoCreado = moduloRecursoServicio.cargarRecurso(moduloId, recursoId, archivo, url);
            log.info("Recurso creado satisfactoriamente con ID: {}", recursoCreado.getId());
            return recursoCreado;
        } catch (IOException e) {
            return new ModuloRecursoDto();
        }
    }

}
