package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.ModelDTO;
import com.radello.glasses_store.domain.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelMapstructMapperTest {

    Model model = Model.M0TT0;
    ModelDTO modelDTO = ModelDTO.M0TT0;

    @Test
    void modeltoModelDto() {

        assertEquals(model.PRICE, modelDTO.PRICE);
    }

}