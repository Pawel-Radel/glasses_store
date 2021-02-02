package com.radello.glasses_store.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Glasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int number;
    private Model model;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_ID")
    @JsonManagedReference
    private Customer customer;

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

    @Override
    public String toString() {
        return "Glasses{" +
                "Id=" + Id +
                ", number=" + number +
                ", model=" + model +
                ", quantity=" + quantity +
                ", customer=" + customer +
                '}';
    }
}
