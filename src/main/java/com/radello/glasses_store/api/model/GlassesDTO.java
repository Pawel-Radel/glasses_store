package com.radello.glasses_store.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlassesDTO {

    Long Id;
    int number;
    ModelDTO model;
    int quantity;
    List<CustomerDTO> listofCustomers = new ArrayList<>();
}
