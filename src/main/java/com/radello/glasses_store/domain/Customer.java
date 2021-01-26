package com.radello.glasses_store.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String name;
    String surname;
    int telephone;
    String city;
    @OneToMany(mappedBy = "customer")
    List<Glasses> listOfGlasses = new ArrayList<>();

}
