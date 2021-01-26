package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.domain.Glasses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    Customer customer;
    CustomerDTO customerDTO;
    List<Glasses> listOfGlasses;
    List<GlassesDTO> listOfGlassesDTO;
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Pawel";
    private static final String CUSTOMER_SURNAME = "Polewski";
    private static final int CUSTOMER_TELEPHONE = 666777888;
    private static final String CUSTOMER_CITY = "Wroclaw";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    @BeforeEach
    void setUp() {
    }

    @Test
    void customerToCustomerDto() {
        listOfGlasses = new ArrayList();
        listOfGlasses.add(new Glasses());
        listOfGlasses.add(new Glasses());
        customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_SURNAME, CUSTOMER_TELEPHONE, CUSTOMER_CITY, listOfGlasses);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);

        assertEquals(CUSTOMER_ID, customerDTO.getID());
        assertEquals(CUSTOMER_NAME, customerDTO.getName());
        assertEquals(CUSTOMER_SURNAME, customerDTO.getSurname());
        assertEquals(CUSTOMER_TELEPHONE, customerDTO.getTelephone());
        assertEquals(CUSTOMER_CITY, customerDTO.getCity());
        assertEquals(2, customerDTO.getListOfGlasses().size());


    }

    @Test
    void customerDtoToCustomer() {

        listOfGlassesDTO = new ArrayList();
        listOfGlassesDTO.add(new GlassesDTO());
        listOfGlassesDTO.add(new GlassesDTO());
        customerDTO = new CustomerDTO(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_SURNAME, CUSTOMER_TELEPHONE, CUSTOMER_CITY, listOfGlassesDTO);

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);


        assertEquals(CUSTOMER_ID, customer.getID());
        assertEquals(CUSTOMER_NAME, customer.getName());
        assertEquals(CUSTOMER_SURNAME, customer.getSurname());
        assertEquals(CUSTOMER_TELEPHONE, customer.getTelephone());
        assertEquals(CUSTOMER_CITY, customer.getCity());
        assertEquals(2, customer.getListOfGlasses().size());
    }
}