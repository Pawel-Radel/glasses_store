package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.domain.Glasses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GlassesMapper {

    GlassesMapper INSTANCE = Mappers.getMapper(GlassesMapper.class);

    @Mapping(target = "customer", qualifiedByName = "Customer")
    GlassesDTO glassesToGlassesDto(Glasses glasses);

  //  @Mapping(target = "customer", qualifiedByName = "Customer")
    Glasses glassesDTOtoGlasses(GlassesDTO glassesDTO);

    @Named("Customer")
    static CustomerDTO setCustomCustomerDTO (Customer customer){

        if (customer == null) return null;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCity(customer.getCity());
        customerDTO.setID(customer.getID());
        customerDTO.setTelephone(customer.getTelephone());
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getName());
        return customerDTO;
    }

}
