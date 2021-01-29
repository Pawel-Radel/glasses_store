package com.radello.glasses_store.service;

import com.radello.glasses_store.api.mappers.GlassesMapper;
import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.repository.GlassesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GlassesServiceImpl implements GlassesService {

    CustomersService customersService;
    GlassesRepository glassesRepository;
    GlassesMapper glassesMapper;

    public GlassesServiceImpl(CustomersService customersService, GlassesRepository glassesRepository, GlassesMapper glassesMapper) {
        this.customersService = customersService;
        this.glassesRepository = glassesRepository;
        this.glassesMapper = glassesMapper;
    }

    @Override
    public List<GlassesDTO> findGlassesByCustomer(Long id) {

        CustomerDTO customerDTO = customersService.findById(id);
        return customerDTO.getListOfGlasses();

    }

    @Override
    public List<GlassesDTO> findBestSellers(Long limit) {

       return glassesRepository
               .findAll(Sort.by(Sort.Direction.DESC, "quantity"))
               .stream()
               .map(glassesMapper::glassesToGlassesDto)
               .limit(limit)
               .collect(Collectors.toList());
    }

    @Override
    public List<GlassesDTO> findWorstSellers(Long limit) {

        return glassesRepository
                .findAll(Sort.by(Sort.Direction.ASC, "quantity"))
                .stream()
                .map(glassesMapper::glassesToGlassesDto)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
