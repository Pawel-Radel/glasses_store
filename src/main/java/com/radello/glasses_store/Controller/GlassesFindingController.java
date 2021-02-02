package com.radello.glasses_store.Controller;

import com.radello.glasses_store.api.mappers.GlassesMapper;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.api.model.GlassesListDTO;
import com.radello.glasses_store.domain.Model;
import com.radello.glasses_store.service.CustomersService;
import com.radello.glasses_store.service.GlassesFindingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GlassesFindingController.BASE_URL)
public class GlassesFindingController {

    public static final String BASE_URL = "/api/glasses/find";

    GlassesFindingService glassesFindingService;
    CustomersService customersService;
    GlassesMapper glassesMapper;

    public GlassesFindingController(GlassesFindingService glassesFindingService,
                                    CustomersService customersService,
                                    GlassesMapper glassesMapper) {
        this.glassesFindingService = glassesFindingService;
        this.customersService = customersService;
        this.glassesMapper = glassesMapper;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getListOfAllCustomers() {
        return new GlassesListDTO(glassesFindingService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesDTO findByID(@PathVariable Long id) {
        return glassesFindingService.findByID(id);
    }

    @GetMapping("/byCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getListByCustomer(@PathVariable Long id) {
        return new GlassesListDTO(glassesFindingService.findGlassesByCustomer(id));
    }

    @GetMapping("/bestSellers/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getBestSellers (@PathVariable Long limit){
        return new GlassesListDTO(glassesFindingService.findBestSellers(limit));
    }

    @GetMapping("/worstSellers/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getWorstSellers (@PathVariable Long limit){
        return new GlassesListDTO(glassesFindingService.findWorstSellers(limit));
    }

    @GetMapping ("/byModel/{model}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getGlassesBymodel (@PathVariable("model") Model model){
      return new GlassesListDTO(glassesFindingService.findAllByModel(model));
    }

}
