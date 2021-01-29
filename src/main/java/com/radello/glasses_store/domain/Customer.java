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
    private Long ID;
    private String name;
    private String surname;
    private int telephone;
    private String city;
    @OneToMany(mappedBy = "customer")
    private List<Glasses> listOfGlasses = new ArrayList<>();

    public Customer addGlasses(Glasses glasses) {

        glasses.setCustomer(this);
        this.listOfGlasses.add(glasses);
        return this;
    }

}
