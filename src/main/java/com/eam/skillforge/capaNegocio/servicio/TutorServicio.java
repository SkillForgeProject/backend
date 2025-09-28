package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;

public interface TutorServicio {

    List<CursoDto> getCursosPorTutorId(Long tutorId);

    Optional<UsuarioDto> getTutorPorId(Long tutorId);

    void eliminarCurso(Long tutorId);
}
