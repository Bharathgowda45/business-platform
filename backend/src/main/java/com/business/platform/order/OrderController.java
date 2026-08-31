package com.business.platform.order;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.web.bind.annotation.*; import java.math.BigDecimal; import java.util.*;
@RestController @RequestMapping("/api/orders")
public class OrderController {
 private final OrderRepository repo; public OrderController(OrderRepository r){repo=r;}
 public record OrderRequest(@NotNull Long customerId,@NotBlank String status,@NotNull @PositiveOrZero BigDecimal totalAmount){}
 @GetMapping public List<CustomerOrder> all(){return repo.findAll();}
 @PostMapping public CustomerOrder create(@Valid @RequestBody OrderRequest x){CustomerOrder o=new CustomerOrder();o.setCustomerId(x.customerId());o.setStatus(x.status());o.setTotalAmount(x.totalAmount());return repo.save(o);}
}
