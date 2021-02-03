package com.radello.glasses_store.api.mappers;

import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.domain.Customer;
import com.radello.glasses_store.domain.Glasses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "listOfGlasses", qualifiedByName = "Customer")
    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    @Named("Customer")
    static List<GlassesDTO> createGlassesDTOList(List<Glasses> list) {
        if (list == null) {
            return null;
        }

        List<GlassesDTO> list1 = new ArrayList<>(list.size());
        for (Glasses glasses : list) {

            GlassesDTO glasses1 = new GlassesDTO();
            glasses1.setId(glasses.getId());
            glasses1.setModel(ModelMapstructMapper.INSTANCE.modelToModelDTO(glasses.getModel()));
            glasses1.setQuantity(glasses.getQuantity());
            list1.add(glasses1);
        }

        return list1;
    }
}
