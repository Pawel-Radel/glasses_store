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
    private Long ID;
    private String name;
    private String surname;
    private int telephone;
    private String city;
    private List<GlassesDTO> listOfGlasses = new ArrayList<>();
}
