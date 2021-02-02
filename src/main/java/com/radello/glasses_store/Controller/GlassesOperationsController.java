package com.radello.glasses_store.Controller;

import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.service.GlassesOperationsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GlassesOperationsController.BASE_URL)
public class GlassesOperationsController {

    GlassesOperationsService glassesOperationsService;

    public GlassesOperationsController(GlassesOperationsService glassesOperationsService) {
        this.glassesOperationsService = glassesOperationsService;
    }

    public static final String BASE_URL = "/api/glasses";

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public GlassesDTO createGlasses(@RequestBody GlassesDTO glassesDTO){
        return glassesOperationsService.createNewGlasses(glassesDTO);

    }

    @PutMapping({"/update/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public GlassesDTO updateGlasses(@PathVariable Long id, @RequestBody GlassesDTO glassesDTO){
        return glassesOperationsService.saveGlassesByDTO(id, glassesDTO);
    }

    @PatchMapping({"/patch/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public GlassesDTO patchCustomer(@PathVariable Long id, @RequestBody GlassesDTO glassesDTO){
        return glassesOperationsService.patchGlasses(id, glassesDTO);
    }

    @DeleteMapping({"/delete/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        glassesOperationsService.deleteGlassesByID(id);
    }
}
