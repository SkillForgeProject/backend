package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;

public interface UsuarioServicio {

    UsuarioDto getUsuarioPorCorreo(String email);
}
