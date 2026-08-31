package com.business.platform.auth;
import com.business.platform.security.JwtService; import com.business.platform.user.*;
import jakarta.validation.Valid; import jakarta.validation.constraints.NotBlank; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AppUserRepository repo; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(AppUserRepository r,PasswordEncoder e,JwtService j){repo=r;encoder=e;jwt=j;}
 public record LoginRequest(@NotBlank String username,@NotBlank String password){}
 @PostMapping("/login") public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req){
   return repo.findByUsername(req.username()).filter(u->u.isEnabled()&&encoder.matches(req.password(),u.getPassword()))
    .<ResponseEntity<?>>map(u->ResponseEntity.ok(Map.of("token",jwt.generate(u.getUsername(),u.getRole()),"username",u.getUsername(),"role",u.getRole())))
    .orElseGet(()->ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Invalid credentials")));
 }
 @GetMapping("/me") public Map<String,Object> me(org.springframework.security.core.Authentication a){return Map.of("username",a.getName(),"authorities",a.getAuthorities());}
}
