package com.radello.glasses_store.api.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
