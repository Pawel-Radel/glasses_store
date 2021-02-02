package com.radello.glasses_store.service;

import com.radello.glasses_store.api.mappers.CustomerMapper;
import com.radello.glasses_store.api.mappers.GlassesMapper;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.domain.Glasses;
import com.radello.glasses_store.repository.GlassesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GlassesOperationsServiceImpl implements GlassesOperationsService {

    GlassesRepository glassesRepository;
    GlassesMapper glassesMapper;
    CustomerMapper customerMapper;

    public GlassesOperationsServiceImpl(GlassesRepository glassesRepository,
                                        GlassesMapper glassesMapper,
                                        CustomerMapper customerMapper) {
        this.glassesRepository = glassesRepository;
        this.glassesMapper = glassesMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public GlassesDTO createNewGlasses(GlassesDTO glassesDTO) {
        return saveAndReturnGlassesDto(glassesMapper.glassesDTOtoGlasses(glassesDTO));
    }

    @Override
    public GlassesDTO saveGlassesByDTO(Long id, GlassesDTO glassesDTO) {

        Glasses glasses = glassesMapper.glassesDTOtoGlasses(glassesDTO);
        glasses.setId(id);
        return saveAndReturnGlassesDto(glasses);
    }

    @Override
    public GlassesDTO patchGlasses(Long id, GlassesDTO glassesDTO) {

        Optional<Glasses> glassesOptional = glassesRepository.findById(id);

        Glasses glasses = glassesOptional.orElseThrow(ResourceNotFoundException::new);

        if (glassesDTO.getQuantity() != 0) glasses.setQuantity(glassesDTO.getQuantity());
        if (glassesDTO.getModel() != null) glasses.setQuantity(glassesDTO.getQuantity());
        if (glassesDTO.getNumber() != 0) glasses.setNumber(glassesDTO.getNumber());
        if (glassesDTO.getCustomer() != null)
            glasses.setCustomer(customerMapper.customerDtoToCustomer(glassesDTO.getCustomer()));

        return glassesMapper.glassesToGlassesDto(glassesRepository.save(glasses));
    }

    @Override
    public void deleteGlassesByID(Long id) {
        glassesRepository.deleteById(id);
    }

    public GlassesDTO saveAndReturnGlassesDto(Glasses glasses) {
        Glasses savedGlasses = glassesRepository.save(glasses);
        return glassesMapper.glassesToGlassesDto(savedGlasses);
    }

}
