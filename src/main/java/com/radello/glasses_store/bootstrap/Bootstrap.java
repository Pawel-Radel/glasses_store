package com.radello.glasses_store.bootstrap;

import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.domain.Glasses;
import com.radello.glasses_store.domain.Model;
import com.radello.glasses_store.repository.CustomerRepository;
import com.radello.glasses_store.repository.GlassesRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CustomerRepository customerRepository;
    private final GlassesRepository glassesRepository;

    public Bootstrap(CustomerRepository customerRepository, GlassesRepository glassesRepository) {
        this.customerRepository = customerRepository;
        this.glassesRepository = glassesRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadCustomers();
        loadGlasses();

    }

    private void loadCustomers() {

        for (long i = 1L; i < 12; i++) {
            createCustomer(i, "Warszawa");
        }
        for (long i = 12L; i < 19; i++) {
            createCustomer(i, "Lodz");
        }
        for (long i = 19L; i < 28; i++) {
            createCustomer(i, "Bialystok");
        }
        for (long i = 28L; i < 34; i++) {
            createCustomer(i, "Lublin");
        }
        for (long i = 34L; i < 40; i++) {
            createCustomer(i, "Poznań");
        }
    }

    private void createCustomer(Long i, String city) {
        Customer customer = new Customer(i, "Name" + i, "Surname" + i, 896840400 + i.intValue(), city, new ArrayList<>());
        customerRepository.save(customer);
    }

    private void loadGlasses() {
        for (long i = 1L; i < 200; i++) {
            loadGlassAndLinkWithCustomer(i);
        }
        for (long i = 200L; i < 400; i++) {
            loadGlassAndLinkWithCustomer(i);
        }
        for (long i = 400L; i < 600; i++) {
            loadGlassAndLinkWithCustomer(i);
        }
        for (long i = 600L; i < 800; i++) {
            loadGlassAndLinkWithCustomer(i);
        }
        for (long i = 800L; i < 1000; i++) {
            loadGlassAndLinkWithCustomer(i);
        }
    }

    private void loadGlassAndLinkWithCustomer(Long i) {
        Customer customer = customerRepository.getOne(1L + (long) (Math.random() * (39L - 1L)));

        Glasses glasses = new Glasses(i, i.intValue(), Model.ASTRAL, i.intValue() * 2, customer);

        customer.getListOfGlasses().add(glasses);
        glassesRepository.save(glasses);
        customerRepository.save(customer);
    }


}
