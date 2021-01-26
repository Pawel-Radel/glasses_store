package com.radello.glasses_store.repository;

import com.radello.glasses_store.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByCityIsLike(String city);

}
