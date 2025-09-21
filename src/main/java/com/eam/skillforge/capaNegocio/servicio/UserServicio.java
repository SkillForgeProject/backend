package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;

import java.util.ArrayList;

public interface UserServicio {
    ArrayList<UsuarioDto>  getUsuarios();
}
