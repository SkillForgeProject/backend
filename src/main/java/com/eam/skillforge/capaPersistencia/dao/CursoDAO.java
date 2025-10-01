package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaPersistencia.mapper.CursoMapper;
import com.eam.skillforge.capaPersistencia.repositorio.CursoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CursoDAO {
    private final CursoRepositorio cursoRepositorio;
    private final CursoMapper cursoMapper;

    public Optional<CursoDto> buscarPorId(Long id) {
        return cursoRepositorio.findById(id)
                .map(cursoMapper::toDTO);
    }

    public boolean eliminarPorId(Long cursoId) {
        if(cursoRepositorio.existsById(cursoId)) {
            cursoRepositorio.deleteById(cursoId);
            return true;
        }

        return false;
    }
}
