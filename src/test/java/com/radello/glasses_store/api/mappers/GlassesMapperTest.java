package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.api.model.ModelDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.domain.Glasses;
import com.radello.glasses_store.domain.Model;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlassesMapperTest {

    Glasses glasses;
    GlassesDTO glassesDTO;
    private final Long GLASSES_ID = 1L;
    private final int GLASSES_NUMBER = 123;
    private final Model GLASSES_MODEL = Model.M0TT0;
    private final ModelDTO GLASSES_MODEL_DTO = ModelDTO.M0TT0;
    private final int GLASSES_QUANTITY = 1;

    GlassesMapper glassesMapper = GlassesMapper.INSTANCE;

    @Test
    void glassesToGlassesDto() {

        glasses = new Glasses(GLASSES_ID, GLASSES_NUMBER, GLASSES_MODEL, GLASSES_QUANTITY, new Customer());

        GlassesDTO glassesDTO = glassesMapper.glassesToGlassesDto(glasses);

        assertEquals(GLASSES_ID, glassesDTO.getId());
        assertEquals(GLASSES_NUMBER, glassesDTO.getNumber());
        assertEquals(GLASSES_MODEL_DTO, glassesDTO.getModel());
        assertEquals(GLASSES_QUANTITY, glassesDTO.getQuantity());
    }

    @Test
    void glassesDtotoGlasses() {

        glassesDTO = new GlassesDTO(GLASSES_ID, GLASSES_NUMBER, GLASSES_MODEL_DTO, GLASSES_QUANTITY, new CustomerDTO());

        Glasses glasses = glassesMapper.glassesDtotoGlasses(glassesDTO);

        assertEquals(GLASSES_ID, glasses.getId());
        assertEquals(GLASSES_NUMBER, glasses.getNumber());
        assertEquals(GLASSES_MODEL, glasses.getModel());
        assertEquals(GLASSES_QUANTITY, glasses.getQuantity());
    }
}