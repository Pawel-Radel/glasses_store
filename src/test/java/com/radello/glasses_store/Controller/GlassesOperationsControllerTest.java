package com.radello.glasses_store.Controller;

import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.api.model.ModelDTO;
import com.radello.glasses_store.service.GlassesOperationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GlassesOperationsControllerTest extends AbstractRestControllerTest {


    MockMvc mvc;

    @InjectMocks
    GlassesOperationsController glassesOperationsController;
    @Mock
    GlassesOperationsService glassesOperationsService;
    GlassesDTO glassesDTO;

    @BeforeEach
    void setUp() {

        glassesDTO = new GlassesDTO();
        glassesDTO.setNumber(123);
        glassesDTO.setQuantity(23);
        glassesDTO.setId(1L);
        glassesDTO.setModel(ModelDTO.ASTRAL);

        mvc = MockMvcBuilders.standaloneSetup(glassesOperationsController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void createGlasses() throws Exception {

        when(glassesOperationsService.createNewGlasses(any(GlassesDTO.class))).thenReturn(glassesDTO);
        mvc.perform(post(GlassesOperationsController.BASE_URL + "/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(glassesDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number", equalTo(123)))
                .andExpect(jsonPath("$.quantity", equalTo(23)))
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(glassesOperationsService, times(1)).createNewGlasses(any(GlassesDTO.class));
    }

    @Test
    void updateGlasses() throws Exception {

        when(glassesOperationsService.saveGlassesByDTO(anyLong(), any(GlassesDTO.class))).thenReturn(glassesDTO);

        mvc.perform(put(GlassesOperationsController.BASE_URL + "/update/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(glassesDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", equalTo(123)))
                .andExpect(jsonPath("$.quantity", equalTo(23)))
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(glassesOperationsService, times(1)).saveGlassesByDTO(anyLong(), any(GlassesDTO.class));
    }

    @Test
    void patchGlasses() throws Exception {

        when(glassesOperationsService.patchGlasses(anyLong(), any(GlassesDTO.class))).thenReturn(glassesDTO);
        mvc.perform(patch(GlassesOperationsController.BASE_URL + "/patch/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(glassesDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", equalTo(123)))
                .andExpect(jsonPath("$.quantity", equalTo(23)))
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(glassesOperationsService, times(1)).patchGlasses(anyLong(), any(GlassesDTO.class));
    }

    @Test
    void deleteGlasses() throws Exception {
        mvc.perform(delete(GlassesOperationsController.BASE_URL + "/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(glassesOperationsService).deleteGlassesByID(anyLong());
    }
}