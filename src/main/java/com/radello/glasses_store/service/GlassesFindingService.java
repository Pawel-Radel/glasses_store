package com.radello.glasses_store.service;

import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.domain.Model;

import java.util.List;

public interface GlassesFindingService {

    List<GlassesDTO> findGlassesByCustomer(Long id);

    List<GlassesDTO> findBestSellers(Long limit);

    List<GlassesDTO> findWorstSellers(Long limit);

    List<GlassesDTO> findAll();

    GlassesDTO findByID(Long id);

    List<GlassesDTO> findAllByModel(Model model);
}
