package com.radello.glasses_store.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {
    List<CustomerDTO> customers;
}
