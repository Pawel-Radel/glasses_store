package com.radello.glasses_store.Controller;


import com.radello.glasses_store.api.model.CustomerDTO;
import com.radello.glasses_store.api.model.CustomerListDTO;
import com.radello.glasses_store.service.CustomersService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Shows list of all customers", notes = "Shows list of all customers")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getListOfAllCustomers() {
        return new CustomerListDTO(customersService.findAll());
    }

    @ApiOperation(value = "Shows customer finded by id", notes = "enter a number to search customer by id")
    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO findById(@PathVariable Long id) {
        return customersService.findById(id);
    }

    @ApiOperation(value = "Shows list of customers fromy city", notes = "Enter a city to print list of customers from this city")
    @GetMapping("/{city}")
    @ResponseStatus(HttpStatus.OK)
    CustomerListDTO getListOfCustomersByCity(@PathVariable String city) {
        return new CustomerListDTO(customersService.findByCity(city));
    }

    @ApiOperation(value = "Create new Customer", notes = "Enter data to create new customer")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customersService.createNewCustomer(customerDTO);
    }


    @ApiOperation(value = "Update customer", notes = "Enter Data to update customer")
    @PutMapping({"/update/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customersService.saveCustomerByDTo(id, customerDTO);
    }

    @ApiOperation(value = "Patch customer", notes = "Enter Data to patch customer")
    @PatchMapping({"/patch/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customersService.patchCustomer(id, customerDTO);
    }

    @ApiOperation(value = "Delete customer", notes = "Enter Data to delete customer")
    @DeleteMapping({"/delete/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customersService.deleteByID(id);
    }
}
