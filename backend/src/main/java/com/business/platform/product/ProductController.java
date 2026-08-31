package com.business.platform.product;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.web.bind.annotation.*; import java.math.BigDecimal; import java.util.*;
@RestController @RequestMapping("/api/products")
public class ProductController {
 private final ProductRepository repo; public ProductController(ProductRepository r){repo=r;}
 public record ProductRequest(@NotBlank String name,@NotBlank String sku,@NotNull @PositiveOrZero BigDecimal price,@PositiveOrZero int stockQuantity){}
 @GetMapping public List<Product> all(){return repo.findAll();}
 @PostMapping public Product create(@Valid @RequestBody ProductRequest x){Product p=new Product();apply(p,x);return repo.save(p);}
 @GetMapping("/{id}") public Product get(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PutMapping("/{id}") public Product update(@PathVariable Long id,@Valid @RequestBody ProductRequest x){Product p=get(id);apply(p,x);return repo.save(p);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
 private void apply(Product p,ProductRequest x){p.setName(x.name());p.setSku(x.sku());p.setPrice(x.price());p.setStockQuantity(x.stockQuantity());}
}
