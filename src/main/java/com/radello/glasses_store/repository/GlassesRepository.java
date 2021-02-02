package com.radello.glasses_store.repository;

import com.radello.glasses_store.domain.Glasses;
import com.radello.glasses_store.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlassesRepository extends JpaRepository<Glasses, Long> {

    List<Glasses> findAllByModel (Model model);
}
