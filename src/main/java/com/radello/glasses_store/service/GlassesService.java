package com.radello.glasses_store.service;

import com.radello.glasses_store.api.model.GlassesDTO;

import java.util.List;

public interface GlassesService {

    List<GlassesDTO> findGlassesByCustomer (Long id);

    List<GlassesDTO> findBestSellers (Long limit);

    List<GlassesDTO> findWorstSellers (Long limit);
}
