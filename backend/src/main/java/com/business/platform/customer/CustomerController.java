package com.business.platform.customer;
import jakarta.validation.Valid; import jakarta.validation.constraints.NotBlank; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/customers")
public class CustomerController {
 private final CustomerRepository repo; public CustomerController(CustomerRepository r){repo=r;}
 public record CustomerRequest(@NotBlank String name,String email,String phone,String address){}
 @GetMapping public List<Customer> all(){return repo.findAll();}
 @PostMapping public Customer create(@Valid @RequestBody CustomerRequest x){Customer c=new Customer();apply(c,x);return repo.save(c);}
 @GetMapping("/{id}") public Customer get(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PutMapping("/{id}") public Customer update(@PathVariable Long id,@Valid @RequestBody CustomerRequest x){Customer c=get(id);apply(c,x);return repo.save(c);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
 private void apply(Customer c,CustomerRequest x){c.setName(x.name());c.setEmail(x.email());c.setPhone(x.phone());c.setAddress(x.address());}
}
