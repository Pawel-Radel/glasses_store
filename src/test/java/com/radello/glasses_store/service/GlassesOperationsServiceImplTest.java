package com.radello.glasses_store.service;

import com.radello.glasses_store.api.mappers.CustomerMapper;
import com.radello.glasses_store.api.mappers.GlassesMapper;
import com.radello.glasses_store.api.mappers.ModelMapstructMapper;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.domain.Glasses;
import com.radello.glasses_store.domain.Model;
import com.radello.glasses_store.repository.GlassesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlassesOperationsServiceImplTest {

    GlassesOperationsServiceImpl glassesOperationsService;
    Glasses glasses;
    Glasses glasses2;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    GlassesMapper glassesMapper = GlassesMapper.INSTANCE;
    GlassesDTO glassesDTO;
    @Mock
    GlassesRepository glassesRepository;
    ModelMapstructMapper modelMapstructMapper;

    @BeforeEach
    void setUp() {
        glasses = new Glasses(1L, 123, Model.ASTRAL, 123, new Customer());
        glassesOperationsService = new GlassesOperationsServiceImpl(glassesRepository, glassesMapper, customerMapper, modelMapstructMapper);
        glassesDTO = new GlassesDTO();
        glassesDTO.setQuantity(11);
        glassesDTO.setNumber(123);
    }


    @Test
    void saveGlassesByDTO() {
        glasses2 = new Glasses();
        glasses2.setQuantity(glassesDTO.getQuantity());
        glasses2.setNumber(glassesDTO.getNumber());

        when(glassesRepository.save(any(Glasses.class))).thenReturn(glasses);

        GlassesDTO glassesDTO1 = glassesOperationsService.saveGlassesByDTO(1L, glassesDTO);

        assertEquals(glasses.getQuantity(), glassesDTO1.getQuantity());
        assertEquals(glasses.getNumber(), glassesDTO1.getNumber());
    }

    @Test
    void patchGlasses() {
        when(glassesRepository.findById(anyLong())).thenReturn(Optional.ofNullable(glasses));
        when(glassesRepository.save(any())).thenReturn(glasses);
        GlassesDTO glassesDTO1 = glassesOperationsService.patchGlasses(1L, glassesDTO);

        assertEquals(glassesDTO.getNumber(), glassesDTO1.getNumber());
        assertEquals(glassesDTO.getQuantity(), glassesDTO1.getQuantity());
        assertEquals(glasses.getId(), glassesDTO1.getId());

        verify(glassesRepository,times(1)).save(any());
        verify(glassesRepository,times(1)).findById(anyLong());
    }

    @Test
    void deleteGlassesByID() {
        Long id = 1L;
        glassesRepository.deleteById(id);
        verify(glassesRepository, times(1)).deleteById(anyLong());
    }
}