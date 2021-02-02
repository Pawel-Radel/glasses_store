package com.radello.glasses_store.service;

import com.radello.glasses_store.api.model.GlassesDTO;

public interface GlassesOperationsService {

    GlassesDTO createNewGlasses (GlassesDTO glassesDTO);

    GlassesDTO saveGlassesByDTO(Long id, GlassesDTO glassesDTO);

    GlassesDTO patchGlasses (Long id, GlassesDTO glassesDTO);

    void deleteGlassesByID (Long id);
}
