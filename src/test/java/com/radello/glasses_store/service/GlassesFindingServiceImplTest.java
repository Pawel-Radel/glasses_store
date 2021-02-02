package com.radello.glasses_store.service;

import com.radello.glasses_store.api.mappers.GlassesMapper;
import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.api.model.ModelDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.domain.Glasses;
import com.radello.glasses_store.domain.Model;
import com.radello.glasses_store.repository.GlassesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlassesFindingServiceImplTest {


    GlassesFindingService glassesFindingService;

    @Mock
    CustomersService customersService;
    @Mock
    GlassesRepository glassesRepository;
    GlassesMapper glassesMapper = GlassesMapper.INSTANCE;
    CustomerDTO customer1;
    CustomerDTO customer2;
    GlassesDTO glassesDTO1;
    GlassesDTO glassesDTO2;
    Glasses glasses1;
    Glasses glasses2;

    @BeforeEach
    void setUp() {
        customer1 = new CustomerDTO(1L, "Pawel", "Watson", 695236235, "Warszawa", new ArrayList<>());
        customer2 = new CustomerDTO(2L, "Tomasz", "Kaminski", 695432535, "Krakow", new ArrayList<>());
        glassesFindingService = new GlassesFindingServiceImpl(customersService, glassesRepository, glassesMapper);
        glassesDTO1 = new GlassesDTO(1L,123, ModelDTO.ASTRAL,10, customer1);
        glassesDTO2 = new GlassesDTO(2L,122, ModelDTO.ELYSION,8, customer1);
        glasses1 = glassesMapper.glassesDTOtoGlasses(glassesDTO1);
        glasses2 = glassesMapper.glassesDTOtoGlasses(glassesDTO2);
    }

    @Test
    void findGlassesByCustomer() {

        customer1.getListOfGlasses().add(glassesDTO1);
        customer1.getListOfGlasses().add(glassesDTO2);

        when(customersService.findById(anyLong())).thenReturn(customer1);

        List list = glassesFindingService.findGlassesByCustomer(1L);

        assertEquals(2, list.size());

        verify(customersService, times(1)).findById(anyLong());
    }

    @Test
    void findBestSellers() {
        Glasses glasses3 = new Glasses(3L, 12, Model.ELYSION, 3, new Customer());
        when(glassesRepository.findAll(Sort.by(Sort.Direction.DESC, "quantity"))).thenReturn(Arrays.asList(glasses1,glasses2,glasses3));
        List<GlassesDTO> list = glassesFindingService.findBestSellers(2L);

        assertEquals(2,list.size());
        assertEquals(10, list.get(0).getQuantity());
        assertEquals(8, list.get(1).getQuantity());
        verify(glassesRepository, times(1)).findAll(Sort.by(Sort.Direction.DESC, "quantity"));
    }

    @Test
    void findWorstSellers() {
        Glasses glasses3 = new Glasses(3L, 12, Model.ELYSION, 3, new Customer());
        when(glassesRepository.findAll(Sort.by(Sort.Direction.ASC, "quantity"))).thenReturn(Arrays.asList(glasses3,glasses2,glasses1));
        List<GlassesDTO> list = glassesFindingService.findWorstSellers(2L);

        assertEquals(2,list.size());
        assertEquals(3, list.get(0).getQuantity());
        assertEquals(8, list.get(1).getQuantity());
        verify(glassesRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "quantity"));
    }

    @Test
    void findAll() {
        when(glassesRepository.findAll()).thenReturn(Arrays.asList(glasses2,glasses1));
        List<GlassesDTO> list = glassesFindingService.findAll();
        assertEquals(2, list.size());
        verify(glassesRepository,times(1)).findAll();
    }

    @Test
    void findByID() {
        when(glassesRepository.findById(anyLong())).thenReturn(Optional.of(glasses1));
        GlassesDTO glasses = glassesFindingService.findByID(1L);

        assertEquals(1, glasses.getId());
        assertEquals(10, glasses.getQuantity());
        assertEquals(ModelDTO.ASTRAL, glasses.getModel());
        assertEquals(123, glasses.getNumber());
    }

}