package com.radello.glasses_store.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Glasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    int number;
    Model model;
    int quantity;

    @ManyToMany
    @JoinTable(name = "Chosen_Glasses_By_Customer",
    joinColumns = {@JoinColumn(name = "Chosen_Glasses_ID")},
    inverseJoinColumns = {@JoinColumn(name ="Customer_ID") })
    List<Customer> listofCustomers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Glasses glasses = (Glasses) o;
        return number == glasses.number &&
                model == glasses.model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model);
    }
}
