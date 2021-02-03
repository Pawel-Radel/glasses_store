package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.ModelDTO;
import com.radello.glasses_store.domain.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapstructMapper {

        ModelMapstructMapper INSTANCE = Mappers.getMapper(ModelMapstructMapper.class);

        ModelDTO modelToModelDTO(Model model);

        Model modelDTOToModel (ModelDTO modelDTO);

}
