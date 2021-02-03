package com.radello.glasses_store.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @ApiModelProperty(value =" Customers Id")
    private Long ID;
    @ApiModelProperty(value =" Customers name")
    private String name;
    @ApiModelProperty(value =" Customers surname")
    private String surname;
    @ApiModelProperty(value =" Customers telephone")
    private int telephone;
    @ApiModelProperty(value =" Customers city")
    private String city;
    @ApiModelProperty(value =" Customers list of glasses")
    private List<GlassesDTO> listOfGlasses = new ArrayList<>();
}
