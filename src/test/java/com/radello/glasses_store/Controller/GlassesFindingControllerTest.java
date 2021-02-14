package com.radello.glasses_store.Controller;

import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.api.model.ModelDTO;
import com.radello.glasses_store.service.GlassesFindingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GlassesFindingControllerTest {

    MockMvc mvc;

    @InjectMocks
    GlassesFindingController glassesFindingController;
    @Mock
    GlassesFindingService glassesFindingService;
    GlassesDTO glassesDTO;
    GlassesDTO glassesDTO2;

    @BeforeEach
    void setUp() {

        glassesDTO = new GlassesDTO();
        glassesDTO.setModel(ModelDTO.ELYSION);
        glassesDTO.setQuantity(10);
        glassesDTO.setNumber(123);
        glassesDTO.setId(1L);
        glassesDTO.setCustomer(new CustomerDTO());

        glassesDTO2 = new GlassesDTO();
        glassesDTO2.setModel(ModelDTO.ASTRAL);
        glassesDTO2.setQuantity(12);
        glassesDTO2.setNumber(1243);
        glassesDTO2.setId(2L);
        glassesDTO2.setCustomer(new CustomerDTO());

        mvc = MockMvcBuilders.standaloneSetup(glassesFindingController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void getListOfAllCustomers() throws Exception {
        GlassesDTO glassesDTO2 = new GlassesDTO();
        glassesDTO2.setModel(ModelDTO.ASTRAL);
        glassesDTO2.setQuantity(12);
        glassesDTO2.setNumber(1243);
        glassesDTO2.setId(2L);
        glassesDTO2.setCustomer(new CustomerDTO());

        when(glassesFindingService.findAll()).thenReturn(Arrays.asList(glassesDTO, glassesDTO2));

        mvc.perform(get(GlassesFindingController.BASE_URL + "/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.glassesDTOList", hasSize(2)));

        verify(glassesFindingService, times(1)).findAll();
    }

    @Test
    void findByID() throws Exception {

        GlassesDTO glasses = new GlassesDTO();
        glasses.setModel(ModelDTO.ASTRAL);
        glasses.setQuantity(12);
        glasses.setNumber(1243);
        glasses.setId(2L);
        glasses.setCustomer(new CustomerDTO());

        when(glassesFindingService.findByID(anyLong())).thenReturn(glasses);

        mvc.perform(get(GlassesFindingController.BASE_URL + "/2")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity", equalTo(12)))
                .andExpect(jsonPath("$.number", equalTo(1243)))
                .andExpect(jsonPath("$.id", equalTo(2)));

        verify(glassesFindingService, times(1)).findByID(anyLong());
    }

    @Test
    void getListByCustomer() throws Exception {

        when(glassesFindingService.findGlassesByCustomer(anyLong())).thenReturn(Arrays.asList(glassesDTO, glassesDTO2));

        mvc.perform(get(GlassesFindingController.BASE_URL + "/byCustomer/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.glassesDTOList", hasSize(2)));

        verify(glassesFindingService, times(1)).findGlassesByCustomer(anyLong());
    }

    @Test
    void getBestSellers() throws Exception {
        when(glassesFindingService.findBestSellers(anyLong())).thenReturn(Arrays.asList(glassesDTO, glassesDTO2));

        mvc.perform(get(GlassesFindingController.BASE_URL + "/bestSellers/2")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.glassesDTOList", hasSize(2)));

        verify(glassesFindingService, times(1)).findBestSellers(anyLong());
    }

    @Test
    void getWorstSellers() throws Exception {

        when(glassesFindingService.findWorstSellers(anyLong())).thenReturn(Arrays.asList(glassesDTO, glassesDTO2));

        mvc.perform(get(GlassesFindingController.BASE_URL + "/worstSellers/2")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.glassesDTOList", hasSize(2)));

        verify(glassesFindingService, times(1)).findWorstSellers(anyLong());
    }

    @Test
    void getGlassesBymodel() throws Exception {

        when(glassesFindingService.findAll()).thenReturn(Arrays.asList(glassesDTO, glassesDTO2));

        mvc.perform(get(GlassesFindingController.BASE_URL + "/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.glassesDTOList", hasSize(2)));

        verify(glassesFindingService, times(1)).findAll();
    }
}