package com.eam.skillforge.capaNegocio.servicio.impl;


import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaNegocio.servicio.MedallaServicio;
import com.eam.skillforge.capaPersistencia.dao.MedallaDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MedallaServicioImpl implements MedallaServicio {
    private final MedallaDAO medallaDAO;

    @Override
    public MedallaDto crearMedalla(MedallaDto medallaDto) {
        log.info("Creando nueva medalla {}", medallaDto.getNombre());

        validarDataParaCrear(medallaDto);

        MedallaDto medallaCreada = medallaDAO.guardar(medallaDto);
        log.info("Medalla creada exitosamente con ID: {}", medallaCreada.getId());

        return medallaCreada;
    }

    /**
     * función para validar la data para poder crear una medalla
     * @param medallaDto: DTO de la medalla
     */
    private void validarDataParaCrear(MedallaDto medallaDto) {
        if(medallaDto.getNombre() == null || medallaDto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

    }
}
