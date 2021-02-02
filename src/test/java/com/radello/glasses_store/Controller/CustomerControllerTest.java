package com.radello.glasses_store.Controller;

import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.service.CustomersService;
import com.radello.glasses_store.service.ResourceNotFoundException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends AbstractRestControllerTest {

    MockMvc mvc;

    @InjectMocks
    CustomerController customerController;
    @Mock
    CustomersService customersService;
    CustomerDTO customer1;

    @BeforeEach
    void setUp() {

        customer1 = new CustomerDTO();
        customer1.setName("Caroline");
        customer1.setSurname("Bright");
        customer1.setTelephone(859682956);
        customer1.setID(1L);
        customer1.setCity("Warsaw");

        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void getListOfAllCustomers() throws Exception {
        CustomerDTO customer2 = new CustomerDTO();
        customer2.setName("Anne");
        customer2.setSurname("Grande");
        customer2.setTelephone(863757499);
        customer2.setID(2L);
        customer2.setCity("Krakow");

        when(customersService.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        customerController.getListOfAllCustomers();

        verify(customersService, times(1)).findAll();

        mvc.perform(get("/" + CustomerController.BASE_URL + "/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void findById() throws Exception {
        when(customersService.findById(anyLong())).thenReturn(customer1);

        mvc.perform(get("/" + CustomerController.BASE_URL + "/find/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Caroline")))
                .andExpect(jsonPath("$.surname", equalTo("Bright")))
                .andExpect(jsonPath("$.telephone", equalTo(859682956)))
                .andExpect(jsonPath("$.city", equalTo("Warsaw")))
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(customersService, times(1)).findById(anyLong());
    }

    @Test
    void createNewCustomer() throws Exception {

        when(customersService.createNewCustomer(any(CustomerDTO.class))).thenReturn(customer1);
        mvc.perform(post("/" + CustomerController.BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Caroline")))
                .andExpect(jsonPath("$.surname", equalTo("Bright")))
                .andExpect(jsonPath("$.telephone", equalTo(859682956)))
                .andExpect(jsonPath("$.city", equalTo("Warsaw")))
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(customersService, times(1)).createNewCustomer(any(CustomerDTO.class));
    }

    @Test
    void updateCustomer() throws Exception {

        when(customersService.saveCustomerByDTo(anyLong(), any(CustomerDTO.class))).thenReturn(customer1);

        mvc.perform(put("/" + CustomerController.BASE_URL + "/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Caroline")))
                .andExpect(jsonPath("$.surname", equalTo("Bright")))
                .andExpect(jsonPath("$.telephone", equalTo(859682956)))
                .andExpect(jsonPath("$.city", equalTo("Warsaw")))
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(customersService, times(1)).saveCustomerByDTo(anyLong(), any());
    }

    @Test
    void patchCustomer() throws Exception{

        when(customersService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customer1);

        mvc.perform(patch("/" + CustomerController.BASE_URL + "/patch/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Caroline")))
                .andExpect(jsonPath("$.surname", equalTo("Bright")))
                .andExpect(jsonPath("$.telephone", equalTo(859682956)))
                .andExpect(jsonPath("$.city", equalTo("Warsaw")))
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(customersService, times(1)).patchCustomer(anyLong(), any());
    }

    @Test
    void deleteCustomer() throws Exception {
        mvc.perform(delete("/" +CustomerController.BASE_URL + "/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customersService).deleteByID(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(customersService.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/" +CustomerController.BASE_URL + "/find/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}