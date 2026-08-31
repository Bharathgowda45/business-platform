package com.business.platform.order;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="customer_order") @Getter @Setter @NoArgsConstructor
public class CustomerOrder { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(name="customer_id") Long customerId; String status; @Column(name="total_amount") BigDecimal totalAmount=BigDecimal.ZERO; @Column(name="created_at") LocalDateTime createdAt; @PrePersist void pre(){createdAt=LocalDateTime.now();}}
