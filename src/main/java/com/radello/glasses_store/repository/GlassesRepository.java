package com.radello.glasses_store.repository;

import com.radello.glasses_store.domain.Glasses;
import com.radello.glasses_store.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GlassesRepository extends JpaRepository<Glasses, Long> {

    List<Glasses> findAllByModel (Model model);
}
