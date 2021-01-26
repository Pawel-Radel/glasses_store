package com.radello.glasses_store.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    Long ID;
    String name;
    String surname;
    int telephone;
    String city;
    List<GlassesDTO> listOfGlasses = new ArrayList<>();
}
