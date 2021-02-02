package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.domain.Glasses;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GlassesMapper {

    GlassesMapper INSTANCE = Mappers.getMapper(GlassesMapper.class);

    GlassesDTO glassesToGlassesDto(Glasses glasses);

    Glasses glassesDTOtoGlasses(GlassesDTO glassesDTO);

}
