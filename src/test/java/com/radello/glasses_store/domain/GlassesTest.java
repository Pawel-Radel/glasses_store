package com.radello.glasses_store.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlassesTest {

    Glasses glasses;
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
        Long GLASSES_ID = 1L;
        assertEquals(GLASSES_ID, glasses.getId());
    }

    @Test
    void getNumber() {
        assertEquals(GLASSES_NUMBER, glasses.getNumber());
    }

    @Test
    void getModel() {
        assertNotNull(glasses.getModel());
        assertEquals(GLASSES_MODEL, glasses.getModel());
    }

    @Test
    void getQuantity() {
        assertEquals(GLASSES_QUANTITY, glasses.getQuantity());
    }

}