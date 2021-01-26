package com.radello.glasses_store.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelListDTO {
    List<ModelDTO> modelDTOList;
}
