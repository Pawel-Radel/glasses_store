package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.ModelDTO;
import com.radello.glasses_store.domain.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {

        ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

        ModelDTO modeltoModelDto (Model model);

        Model modelDtotoModel (ModelDTO modelDTO);

}
