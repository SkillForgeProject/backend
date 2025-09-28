package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;

import java.util.ArrayList;

public interface AdministradorServicio {
    UsuarioDto postUsuario(UsuarioDto usuario);
    void deleteUsuario(Long id);
    Integer getCantidadUsuarios();
}
