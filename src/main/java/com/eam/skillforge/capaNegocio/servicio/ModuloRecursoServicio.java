package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ModuloRecursoServicio {

    ModuloRecursoDto cargarRecurso(Long moduloId, Long CursoId, MultipartFile archivo, String url) throws IOException;

    List<ModuloRecursoDto> getRecursosPorModulo(Long moduloId);
}
