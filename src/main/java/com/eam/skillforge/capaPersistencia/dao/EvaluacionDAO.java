package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.EvaluacionDto;
import com.eam.skillforge.capaPersistencia.mapper.EvaluacionMapper;
import com.eam.skillforge.capaPersistencia.repositorio.EvaluacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EvaluacionDAO {

    private final EvaluacionRepositorio evaluacionRepositorio;
    private final EvaluacionMapper evaluacionMapper;

    public Optional<EvaluacionDto> buscarPorId(Long id) {
        return evaluacionRepositorio.findById(id)
                .map(evaluacionMapper::toDto);
    }
}
