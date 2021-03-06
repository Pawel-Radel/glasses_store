package com.radello.glasses_store.service;

import com.radello.glasses_store.api.model.CustomerDTO;

import java.util.List;

public interface CustomersService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(Long id);

    List<CustomerDTO> findByCity (String string);

    CustomerDTO createNewCustomer (CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTo(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer (Long id, CustomerDTO customerDTO);

    void deleteByID (Long id);
}
