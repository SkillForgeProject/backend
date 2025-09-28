package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.dto.UsuariosPorCursoMesDTO;
import com.eam.skillforge.capaNegocio.excepciones.CreacionUsuarioExcepcion;
import com.eam.skillforge.capaNegocio.servicio.AdministradorServicio;
import com.eam.skillforge.capaPersistencia.dao.AdministradorDAO;
import com.eam.skillforge.capaPersistencia.repositorio.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdministradorServicioImpl implements AdministradorServicio {
    private final AdministradorDAO administradorDAO;
    @Autowired
    private UsuarioRepositorio usuarioRepository;

    public UsuarioDto postUsuario(UsuarioDto usuario) {
        System.out.println("Se inicia guardado");
        if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
            throw new CreacionUsuarioExcepcion("Es necesaria una contraseña para el usuario");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new CreacionUsuarioExcepcion("Es necesario un nombre para el usuario");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new CreacionUsuarioExcepcion("Es necesario un email para el usuario");
        }

        if (!usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new CreacionUsuarioExcepcion("El formato del correo es inválido");
        }

        if (usuario.getId_rol() == null || usuario.getId_rol().getId() <= 0) {
            throw new CreacionUsuarioExcepcion("Es necesario un rol válido para el usuario");
        }

        if (usuario.getDepartamento() == null || usuario.getDepartamento().getId() <= 0) {
            throw new CreacionUsuarioExcepcion("Es necesario un departamento válido para el usuario");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new CreacionUsuarioExcepcion("Ya existe un usuario con el email: " + usuario.getEmail());
        }
        UsuarioDto usuarioCreado = administradorDAO.guardarUsuario(usuario);
        return usuarioCreado;
    }

    public void deleteUsuario(Long id) {
        administradorDAO.borrarUsuario(id);
    }

    public Integer getCantidadUsuarios() {
        return administradorDAO.obtenerCantidadUsuarios();
    }

    public Double getTasaFinalizacion() { return administradorDAO.obtenerTasaFinalizacion();}
    public List<UsuariosPorCursoMesDTO> getUsuariosPorCursoMes() { return administradorDAO.obtenerUsuariosPorCursoMes();}
}
