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
    List<Customer> customerList;
    List<CustomerDTO> customerListDTO;
    private final Long GLASSES_ID = 1L;
    private final int GLASSES_NUMBER = 123;
    private final Model GLASSES_MODEL = Model.M0TT0;
    private final ModelDTO GLASSES_MODEL_DTO = ModelDTO.M0TT0;
    private final int GLASSES_QUANTITY = 1;
    GlassesMapper glassesMapper = GlassesMapper.INSTANCE;

    @Test
    void glassesToGlassesDto() {
        customerList=new ArrayList<>();
        customerList.add(new Customer());
        customerList.add(new Customer());
        glasses = new Glasses(GLASSES_ID, GLASSES_NUMBER, GLASSES_MODEL, GLASSES_QUANTITY, customerList);

        GlassesDTO glassesDTO = glassesMapper.glassesToGlassesDto(glasses);

        assertEquals(GLASSES_ID, glassesDTO.getId());
        assertEquals(GLASSES_NUMBER, glassesDTO.getNumber());
        assertEquals(GLASSES_MODEL_DTO, glassesDTO.getModel());
        assertEquals(GLASSES_QUANTITY, glassesDTO.getQuantity());
        assertEquals(2, glassesDTO.getListofCustomers().size());

    }

    @Test
    void glassesDtotoGlasses() {

        customerListDTO=new ArrayList<>();
        customerListDTO.add(new CustomerDTO());
        customerListDTO.add(new CustomerDTO());
        glassesDTO = new GlassesDTO(GLASSES_ID, GLASSES_NUMBER, GLASSES_MODEL_DTO, GLASSES_QUANTITY, customerListDTO);

        Glasses glasses = glassesMapper.glassesDtotoGlasses(glassesDTO);

        assertEquals(GLASSES_ID, glasses.getId());
        assertEquals(GLASSES_NUMBER, glasses.getNumber());
        assertEquals(GLASSES_MODEL, glasses.getModel());
        assertEquals(GLASSES_QUANTITY, glasses.getQuantity());
        assertEquals(2, glasses.getListofCustomers().size());
    }
}