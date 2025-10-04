package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.dto.UsuariosPorCursoMesDTO;
import com.eam.skillforge.capaNegocio.excepciones.UsuarioNoEncontradoExcepcion;
import com.eam.skillforge.capaPersistencia.entidad.Rol;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.DepartamentoRepositorio;
import com.eam.skillforge.capaPersistencia.repositorio.RolRepositorio;
import com.eam.skillforge.capaPersistencia.repositorio.AdministradorRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdministradorDAO {
    private final AdministradorRepositorio usuarioRepositorio;
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
        usuarioRepositorio.findById(id).ifPresentOrElse(
            usuario -> {
                usuarioRepositorio.delete(usuario);
            },
            () -> {
                throw new UsuarioNoEncontradoExcepcion(id);
            }
        );
    }

    public Integer obtenerCantidadUsuarios() {
        return Math.toIntExact(usuarioRepositorio.contarPorRol(3L));
    }

    public Double obtenerTasaFinalizacion() {
        return usuarioRepositorio.findPromedioProgresoDistinto();
    }

    public List<UsuariosPorCursoMesDTO> obtenerUsuariosPorCursoMes() {
        return usuarioRepositorio.contarUsuariosPorCursoYMes();
    }
}
