package com.radello.glasses_store.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlassesDTO {

    @ApiModelProperty(value =" Glasses Id")
    private Long Id;
    @ApiModelProperty(value =" Glasses number")
    private int number;
    @ApiModelProperty(value =" Glasses model")
    private ModelDTO model;
    @ApiModelProperty(value =" Glasses quantity")
    private int quantity;
    @ApiModelProperty(value =" Glasses customer")
    private CustomerDTO customer;
}
