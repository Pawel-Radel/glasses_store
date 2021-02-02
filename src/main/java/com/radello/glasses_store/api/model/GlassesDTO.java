package com.radello.glasses_store.api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlassesDTO {

    private Long Id;
    private int number;
    private ModelDTO model;
    private int quantity;
    private CustomerDTO customer;
}
