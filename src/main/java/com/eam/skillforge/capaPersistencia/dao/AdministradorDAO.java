package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Departamento;
import com.eam.skillforge.capaPersistencia.entidad.Rol;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.DepartamentoRepositorio;
import com.eam.skillforge.capaPersistencia.repositorio.RolRepositorio;
import com.eam.skillforge.capaPersistencia.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdministradorDAO {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;
    private final RolRepositorio rolRepositorio;
    private final DepartamentoRepositorio departamentoRepositorio;

    public UsuarioDto guardarUsuario(UsuarioDto usuario) {
        System.out.print("Se crea entidad");
        Usuario usuarioEntidad = usuarioMapper.toEntidad(usuario);
        Rol rol = rolRepositorio.findById(usuario.getId_rol().getId())
                .orElseThrow(() -> new RuntimeException("Rol no  encontrado"));
        usuarioEntidad.setIdRol(rol);

        System.out.print("se crea usuario");
        System.out.print(usuarioEntidad.getEmail());
        Usuario entidadGuardada = usuarioRepositorio.save(usuarioEntidad);
        return usuarioMapper.toDto(entidadGuardada);
    }

    public void borrarUsuario(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuarioEntidad = usuarioOpt.get();
            usuarioRepositorio.delete(usuarioEntidad);
            // aquí ya tienes el usuario
        } else {
            // no existe el usuario con ese id
        }
    }

}
