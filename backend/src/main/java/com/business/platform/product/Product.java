package com.business.platform.product;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor
public class Product { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String name; @Column(nullable=false,unique=true) String sku; @Column(nullable=false) BigDecimal price; @Column(name="stock_quantity") int stockQuantity; boolean active=true; @Column(name="created_at") LocalDateTime createdAt; @Column(name="updated_at") LocalDateTime updatedAt; @PrePersist void pre(){createdAt=updatedAt=LocalDateTime.now();}@PreUpdate void upd(){updatedAt=LocalDateTime.now();}}
