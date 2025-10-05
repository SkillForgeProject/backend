package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.CreacionEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.excepciones.UsuarioNoAutorizadoExcepcion;
import com.eam.skillforge.capaNegocio.servicio.CursoServicio;
import com.eam.skillforge.capaNegocio.servicio.EvaluacionServicio;
import com.eam.skillforge.capaNegocio.servicio.ModuloServicio;
import com.eam.skillforge.capaNegocio.servicio.TutorServicio;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
import com.eam.skillforge.capaPersistencia.dao.TutorDAO;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TutorServicioImpl implements TutorServicio {

    private final TutorDAO tutorDAO;
    private final CursoDAO cursoDAO;
    private final CursoServicio cursoServicio;
    private final ModuloServicio moduloServicio;

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
        return null;
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
}
