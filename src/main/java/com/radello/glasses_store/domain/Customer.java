package com.radello.glasses_store.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Glasses> listOfGlasses = new ArrayList<>();

    public Customer addGlasses(Glasses glasses) {

        glasses.setCustomer(this);
        this.listOfGlasses.add(glasses);
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephone=" + telephone +
                ", city='" + city + '\'' +
                '}';
    }
}
