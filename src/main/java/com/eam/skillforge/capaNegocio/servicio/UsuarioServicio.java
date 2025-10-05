package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;

import java.util.List;

public interface UsuarioServicio {

    UsuarioDto getUsuarioPorCorreo(String email);

    UsuarioDto buscarPorId(Long usuarioId);

    List<CursoDto> getCursosPorNivel(Long nivelId);
}
