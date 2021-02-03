package com.radello.glasses_store.Controller;

import com.radello.glasses_store.api.mappers.GlassesMapper;
import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.api.model.GlassesListDTO;
import com.radello.glasses_store.domain.Model;
import com.radello.glasses_store.service.CustomersService;
import com.radello.glasses_store.service.GlassesFindingService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Shows list of all glasses", notes = "Shows list of all glasses")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getListOfAllCustomers() {
        return new GlassesListDTO(glassesFindingService.findAll());
    }

    @ApiOperation(value = "Shows glasses by id", notes = "enter number to show glasses by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesDTO findByID(@PathVariable Long id) {
        return glassesFindingService.findByID(id);
    }

    @ApiOperation(value = "Shows list of customers glasses", notes = "Enter customer id to show his list of glasses")
    @GetMapping("/byCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getListByCustomer(@PathVariable Long id) {
        return new GlassesListDTO(glassesFindingService.findGlassesByCustomer(id));
    }

    @ApiOperation(value = "Shows list of bestsellers", notes = "Limit indicates size of list")
    @GetMapping("/bestSellers/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getBestSellers(@PathVariable Long limit) {
        return new GlassesListDTO(glassesFindingService.findBestSellers(limit));
    }

    @ApiOperation(value = "Shows list of worstSellers", notes = "Limit indicates size of list")
    @GetMapping("/worstSellers/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getWorstSellers(@PathVariable Long limit) {
        return new GlassesListDTO(glassesFindingService.findWorstSellers(limit));
    }

    @ApiOperation(value = "Shows list of glasses By model", notes = "Enter model to show glasses of this model")
    @GetMapping("/byModel/{model}")
    @ResponseStatus(HttpStatus.OK)
    public GlassesListDTO getGlassesBymodel(@PathVariable("model") Model model) {
        return new GlassesListDTO(glassesFindingService.findAllByModel(model));
    }

}
