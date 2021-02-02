package com.radello.glasses_store.Controller;


import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.CustomerListDTO;
import com.radello.glasses_store.service.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "api/customers";

    CustomersService customersService;

    public CustomerController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getListOfAllCustomers() {
        return new CustomerListDTO(customersService.findAll());
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO findById (@PathVariable Long id){
        return customersService.findById(id);
    }

    @GetMapping("/{city}")
    @ResponseStatus(HttpStatus.OK)
    CustomerListDTO getListOfCustomersByCity (@PathVariable String city) {
        return new CustomerListDTO(customersService.findByCity(city));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customersService.createNewCustomer(customerDTO);
    }

    @PutMapping({"/update/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customersService.saveCustomerByDTo(id, customerDTO);
    }

    @PatchMapping({"/patch/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customersService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping({"/delete/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customersService.deleteByID(id);
    }
}
