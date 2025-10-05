package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ModuloRecursoServicio {

    ModuloRecursoDto cargarRecurso(Long moduloId, Long CursoId, MultipartFile archivo, String url) throws IOException;
}
