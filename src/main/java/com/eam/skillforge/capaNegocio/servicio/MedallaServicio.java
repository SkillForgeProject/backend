package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;

import java.util.List;

public interface MedallaServicio {
    //TODO: Relación Medalla - Usuario es M:N, se necesita tabla intermedia
    //TODO: El ícono se debe dejar por defecto en función de la medalla?

    MedallaDto crearMedalla(MedallaDto medallaDto);

    List<MedallaDto> getMedallasPorUsuarioId(Long id);
}
