package com.radello.glasses_store.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerTest {

    Customer customer;
    List<Glasses> listOfGlasses;
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Pawel";
    private static final String CUSTOMER_SURNAME = "Polewski";
    private static final int CUSTOMER_TELEPHONE = 666777888;
    private static final String CUSTOMER_CITY = "Wroclaw";

    @BeforeEach
    void setUp() {
        listOfGlasses = new ArrayList<>();
        listOfGlasses.add(new Glasses());
        listOfGlasses.add(new Glasses());
        customer = new Customer(CUSTOMER_ID,CUSTOMER_NAME ,CUSTOMER_SURNAME ,CUSTOMER_TELEPHONE , CUSTOMER_CITY, listOfGlasses);
    }

    @Test
    void getID() {

        assertNotNull(customer.getID());
        assertEquals(CUSTOMER_ID, customer.getID());
    }

    @Test
    void getName() {
        assertNotNull(customer.getName());
        assertEquals(CUSTOMER_NAME, customer.getName());
    }

    @Test
    void getSurname() {
        assertNotNull(customer.getSurname());
        assertEquals(CUSTOMER_SURNAME, customer.getSurname());
    }

    @Test
    void getTelephone() {
        assertNotNull(customer.getTelephone());
        assertEquals(CUSTOMER_TELEPHONE, customer.getTelephone());
    }

    @Test
    void getCity() {
        assertNotNull(customer.getCity());
        assertEquals(CUSTOMER_CITY, customer.getCity());
    }

    @Test
    void getListOfGlasses() {
        assertNotNull(customer.getListOfGlasses());
        assertEquals(2, customer.getListOfGlasses().size());
    }
}