package com.business.platform.customer;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor
public class Customer { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String name; String email; String phone; String address; @Column(name="created_at") LocalDateTime createdAt; @Column(name="updated_at") LocalDateTime updatedAt; @PrePersist void pre(){createdAt=updatedAt=LocalDateTime.now();} @PreUpdate void upd(){updatedAt=LocalDateTime.now();}}
