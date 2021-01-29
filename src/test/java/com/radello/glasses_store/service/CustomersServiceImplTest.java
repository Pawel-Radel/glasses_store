package com.radello.glasses_store.service;

import com.radello.glasses_store.api.mappers.CustomerMapper;
import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomersServiceImplTest {

    Customer customer1;
    Customer customer2;

    CustomersService service;
    @Mock
    CustomerRepository repository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        customer1 = new Customer(1L, "Pawel", "Watson", 695236235, "Warszawa", new ArrayList<>());
        customer2 = new Customer(2L, "Tomasz", "Kaminski", 695432535, "Krakow", new ArrayList<>());
        service = new CustomersServiceImpl(customerMapper, repository);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<CustomerDTO> list = service.findAll();

        assertEquals(2, list.size());
        assertEquals("Pawel", list.get(0).getName());
        assertEquals("Krakow", list.get(1).getCity());

        verify(repository,times(1)).findAll();
    }

    @Test
    void findById() {

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(customer1));

        CustomerDTO customerDTO = service.findById(1L);

        assertEquals("Pawel", customerDTO.getName());
        assertEquals("Warszawa",customerDTO.getCity());
        assertEquals("Watson",customerDTO.getSurname());
    }


    @Test
    void saveCustomerByDTo() {

        CustomerDTO customerDTO =new CustomerDTO();
        customerDTO.setName("Lukas");
        customerDTO.setSurname("Molevsky");
        customerDTO.setTelephone(18904323);
        customerDTO.setCity("Olsztyn");

        customer2.setCity(customerDTO.getCity());
        customer2.setTelephone(customerDTO.getTelephone());
        customer2.setSurname(customerDTO.getSurname());
        customer2.setName(customerDTO.getName());

        when(repository.save(any(Customer.class))).thenReturn(customer2);
        CustomerDTO customerDTO1 = service.saveCustomerByDTo(1L,customerDTO);

        assertEquals(customerDTO.getName(), customerDTO1.getName());
        assertEquals(customerDTO.getSurname(), customerDTO1.getSurname());
        assertEquals(customerDTO.getTelephone(), customerDTO1.getTelephone());
        assertEquals(customerDTO.getCity(), customerDTO1.getCity());

        verify(repository,times(1)).save(any());
    }

    @Test
    void patchCustomer() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCity("Bialystok");
        customerDTO.setTelephone(372584923);

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(customer2));
        when(repository.save(any())).thenReturn(customer2);
        CustomerDTO customerDTO1 = service.patchCustomer(1L, customerDTO);

        assertEquals(customerDTO.getCity(), customerDTO1.getCity());
        assertEquals(customerDTO.getTelephone(), customerDTO1.getTelephone());
        assertEquals(customer2.getName(),customerDTO1.getName());
        assertEquals(customer2.getSurname(),customerDTO1.getSurname());
        assertEquals(customer2.getID(),customerDTO1.getID());

        verify(repository,times(1)).save(any());
        verify(repository,times(1)).findById(anyLong());
    }

    @Test
    void deleteByID() {

        Long id = 1L;
        repository.deleteById(id);
        verify(repository, times(1)).deleteById(anyLong());

    }

    @Test
    void findByCity() {
        Customer customer3 = new Customer();
        customer3.setCity("Warszawa");

        when(repository.findAllByCityIsLike(anyString())).thenReturn(Arrays.asList(customer1, customer3));
        List<CustomerDTO> list = service.findByCity("Warszawa");

        assertEquals(2, list.size());
        verify(repository,times(1)).findAllByCityIsLike(anyString());
    }
}