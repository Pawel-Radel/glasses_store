package com.radello.glasses_store.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlassesTest {

    Glasses glasses;
    List<Customer> customerList;
    private final Long GLASSES_ID = 1L;
    private final int GLASSES_NUMBER = 123;
    private final Model GLASSES_MODEL = Model.M0TT0;
    private final int GLASSES_QUANTITY = 1;


    @BeforeEach
    void setUp() {

        glasses = new Glasses(1L,GLASSES_NUMBER, GLASSES_MODEL, GLASSES_QUANTITY, new Customer());
    }

    @Test
    void testEquals() {
        Glasses glasses2 = new Glasses(2L, GLASSES_NUMBER, GLASSES_MODEL, 3, new Customer());
        assertEquals(glasses,glasses2);
    }

    @Test
    void getId() {
        assertNotNull(glasses.getId());
        assertEquals(GLASSES_ID, glasses.getId());
    }

    @Test
    void getNumber() {
        assertNotNull(glasses.getNumber());
        assertEquals(GLASSES_NUMBER, glasses.getNumber());
    }

    @Test
    void getModel() {
        assertNotNull(glasses.getModel());
        assertEquals(GLASSES_MODEL, glasses.getModel());
    }

    @Test
    void getQuantity() {
        assertNotNull(glasses.getQuantity());
        assertEquals(GLASSES_QUANTITY, glasses.getQuantity());
    }

}