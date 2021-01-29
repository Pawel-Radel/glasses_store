package com.radello.glasses_store.service;


import com.radello.glasses_store.repository.CustomerRepository;
import com.radello.glasses_store.repository.GlassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@DataJpaTest
public class CustomerServiceImlIT {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    GlassesRepository glassesRepository;


}
