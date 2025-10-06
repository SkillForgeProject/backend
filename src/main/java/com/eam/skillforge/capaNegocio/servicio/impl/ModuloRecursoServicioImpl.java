package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import com.eam.skillforge.capaNegocio.servicio.ModuloRecursoServicio;
import com.eam.skillforge.capaPersistencia.dao.ModuloRecursoDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ModuloRecursoServicioImpl implements ModuloRecursoServicio {

    private final ModuloRecursoDAO moduloRecursoDAO;
    private final String BASE_PATH = System.getProperty("user.dir") + File.separator + "archivos";

    @Override
    public ModuloRecursoDto cargarRecurso(Long moduloId, Long recursoId, MultipartFile archivo, String url) throws IOException {

        if((archivo == null || archivo.isEmpty()) && (url == null || url.isBlank())) {
            throw new IllegalArgumentException("Debe enviar un archivo o una URL");
        }

        ModuloRecursoDto recurso = new ModuloRecursoDto();
        recurso.setModuloId(moduloId);
        recurso.setRecursoId(recursoId);

        if(archivo != null && !archivo.isEmpty()) {
            Path carpeta = Paths.get(BASE_PATH, "modulos", String.valueOf(moduloId));
            if(!Files.exists(carpeta)) Files.createDirectories(carpeta);

            String nombreArchivo = archivo.getOriginalFilename();
            Path rutaArchivo = carpeta.resolve(nombreArchivo);
            Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
            String rutaRelativa = "archivos/modulos" + moduloId + "/" + nombreArchivo;
            recurso.setRutaArchivo(rutaRelativa);
            recurso.setUrl(null);

            log.info("Recurso con ARCHIVO creado listo para guardar: {}", recurso);
            ModuloRecursoDto guardado = moduloRecursoDAO.guardarRecurso(recurso);
            log.info("Recurso con ARCHIVO guardado exitosamente con ID: {}", guardado.getId());

            return guardado;
        }

        if(esUrlValida(url)) {
            recurso.setUrl(url);
            recurso.setRutaArchivo(null);
            log.info("Recurso con URL creado listo para guardar: {}", recurso);
            ModuloRecursoDto guardado = moduloRecursoDAO.guardarRecurso(recurso);
            log.info("Recurso con URL guardado exitosamente con ID: {}", guardado.getId());
            return guardado;
        } else {
            throw new IllegalArgumentException("La URL no es válida: " + url);
        }
    }

    @Override
    public List<ModuloRecursoDto> getRecursosPorModulo(Long moduloId) {
        log.debug("Obteniendo todos los recursos del modulo ID: {}", moduloId);
        return moduloRecursoDAO.getRecursosPorModulo(moduloId);
    }

    private boolean esUrlValida(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
