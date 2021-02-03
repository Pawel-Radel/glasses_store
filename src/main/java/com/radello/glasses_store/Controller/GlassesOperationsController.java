package com.radello.glasses_store.Controller;

import com.radello.glasses_store.api.model.GlassesDTO;
import com.radello.glasses_store.service.GlassesOperationsService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Create new Glasses", notes = "Enter data to create new Glasses")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public GlassesDTO createGlasses(@RequestBody GlassesDTO glassesDTO) {
        return glassesOperationsService.createNewGlasses(glassesDTO);

    }

    @ApiOperation(value = "Update  Glasses", notes = "Enter data to update  Glasses")
    @PutMapping({"/update/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public GlassesDTO updateGlasses(@PathVariable Long id, @RequestBody GlassesDTO glassesDTO) {
        return glassesOperationsService.saveGlassesByDTO(id, glassesDTO);
    }

    @ApiOperation(value = "Patch  Glasses", notes = "Enter data to patch  Glasses")
    @PatchMapping({"/patch/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public GlassesDTO patchGlasses(@PathVariable Long id, @RequestBody GlassesDTO glassesDTO) {
        return glassesOperationsService.patchGlasses(id, glassesDTO);
    }

    @ApiOperation(value = "Delete Glasses", notes = "Enter data to delete  Glasses")
    @DeleteMapping({"/delete/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteGlasses(@PathVariable Long id) {
        glassesOperationsService.deleteGlassesByID(id);
    }
}
