package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.excepciones.UsuarioNoAutorizadoExcepcion;
import com.eam.skillforge.capaNegocio.servicio.CursoServicio;
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

    @Override
    public List<CursoDto> getCursosPorTutorId(Long tutorId) {
        log.debug("Buscando cursos por ID de tutor: {}", tutorId);

        getTutorPorId(tutorId);

        return tutorDAO.getCursosPorTutorId(tutorId);
    }

    @Override
    public Optional<UsuarioDto> getTutorPorId(Long tutorId) {
        log.debug("Buscando tutor por ID: {}", tutorId);

        if(tutorId != 2) {
            throw new UsuarioNoAutorizadoExcepcion("Este usuario no tiene permisos para utilizar este endpoint");
        }

        return tutorDAO.getTutorPorId(tutorId);
    }

    @Override
    public void eliminarCurso(Long cursoId) {
        log.debug("Eliminando curso ID: {}", cursoId);
        cursoServicio.getCursoPorId(cursoId);

        boolean eliminado = cursoDAO.eliminarPorId(cursoId);
        if(!eliminado) {
            throw new RuntimeException("Error al eliminar curso por ID: " + cursoId);
        }

        log.info("Curso eliminado satisfactoriamente ID: {}", cursoId);
    }
}
