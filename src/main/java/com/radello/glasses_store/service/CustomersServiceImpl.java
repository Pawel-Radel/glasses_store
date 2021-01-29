package com.radello.glasses_store.service;

import com.radello.glasses_store.api.mappers.CustomerMapper;
import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomersServiceImpl implements CustomersService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomersServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {

        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long id) {

        Optional<CustomerDTO> customerDTO = customerRepository.findById(id).map(customerMapper::customerToCustomerDto);
        return customerDTO.orElseThrow();
    }

    @Override
    public List<CustomerDTO> findByCity(String string) {
        return customerRepository.findAllByCityIsLike(string)
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDTO saveAndReturnDTO(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDTO saveCustomerByDTo(Long id, CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setID(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            return customerDTO;
        }

        Customer customer = customerOptional.get();

        if (customerDTO.getName() != null) {
            customer.setName(customerDTO.getName());
        }

        if (customerDTO.getSurname() != null) {
            customer.setSurname(customerDTO.getSurname());
        }

        if (customerDTO.getCity() != null) {
            customer.setCity(customerDTO.getCity());
        }

        if (customerDTO.getTelephone() != 0) {
            customer.setTelephone(customerDTO.getTelephone());
        }

        return customerMapper.customerToCustomerDto(customerRepository.save(customer));
    }

    @Override
    public void deleteByID(Long id) {
        customerRepository.deleteById(id);
    }

}