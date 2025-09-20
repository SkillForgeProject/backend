package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;

public interface MedallaServicio {
    //TODO: Relación Medalla - Usuario es M:N, se necesita tabla intermedia
    //TODO: El ícono se debe dejar por defecto en función de la medalla?

    MedallaDto crearMedalla(MedallaDto medallaDto);
}
