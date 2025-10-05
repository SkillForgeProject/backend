package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.CursoDiferenciaUsuariosDto;
import com.eam.skillforge.capaNegocio.dto.TopCursoDto;
import com.eam.skillforge.capaNegocio.servicio.CursoServicio;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CursoServicioImpl implements CursoServicio {

    private final CursoDAO cursoDAO;

    @Override
    @Transactional(readOnly = true)
    public CursoDto getCursoPorId(Long cursoId) {
        log.debug("Buscando curso por ID: {}", cursoId);

        return cursoDAO.buscarPorId(cursoId)
                .orElseThrow(() -> {
                    log.warn("Curso no encontrado con ID: {}", cursoId);
                    return new com.eam.skillforge.capaNegocio.excepciones.CursoNoEncontradoExcepcion(cursoId);
                });
    }

    @Override
    public List<CursoDto> buscarCursosPorNivel(Long nivelId) {
        return cursoDAO.buscarPorNivel(nivelId)
                .orElseThrow(() -> {
                    log.warn("Cursos no encontrados por ID de nivel: {}", nivelId);
                    return new com.eam.skillforge.capaNegocio.excepciones.CursoNoEncontradoExcepcion("Cursos no encontrados para el nivel con ID: " + nivelId);
                });
    }

    @Override
    public CursoDto actualizarCurso(Long id, CursoDto curso) {
        log.info("Actualizando curso ID: {}", id);
        getCursoPorId(id);

        validarDataCurso(curso);

        return cursoDAO.actualizar(id, curso)
                .orElseThrow(() -> new com.eam.skillforge.capaNegocio.excepciones.DatosInvalidosExcepcion("Error al actualizar el curso con ID: " + id));

    }

    @Override
    @Transactional(readOnly = true)
    public List<TopCursoDto> getTopCursosMejoresPuntuados() {
        log.debug("Obteniendo los 3 cursos mejor puntuados");
        
        List<TopCursoDto> topCursos = cursoDAO.getTopCursosMejoresPuntuados();
        
        log.info("Se encontraron {} cursos mejor puntuados", topCursos.size());
        return topCursos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDiferenciaUsuariosDto> getCursosMasTomados() {
        log.debug("Obteniendo los 6 cursos con menor diferencia de usuarios");
        
        List<CursoDiferenciaUsuariosDto> cursosConDiferencia = cursoDAO.getCursosMasTomados();
        
        log.info("Se encontraron {} cursos con diferencia de usuarios", cursosConDiferencia.size());
        return cursosConDiferencia;
    }

    private void validarDataCurso(CursoDto curso) {

    }

    @Override
    @Transactional
    public List<CursoDto> getCursosActivos(){
        log.debug("Obteniendo los cursos activos");

        List<CursoDto> CursosActivos = cursoDAO.getCursosActivos();

        log.info("Se encontraron los cursos activos");
        return CursosActivos;
    }
}
