package com.business.platform.security;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException; import java.util.List;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
 private final JwtService jwt;
 public JwtAuthFilter(JwtService jwt){this.jwt=jwt;}
 protected void doFilterInternal(HttpServletRequest r,HttpServletResponse s,FilterChain c)throws ServletException,IOException{
   String h=r.getHeader("Authorization");
   if(h!=null&&h.startsWith("Bearer ")){String t=h.substring(7); if(jwt.valid(t)){
     var cl=jwt.claims(t); var auth=new UsernamePasswordAuthenticationToken(cl.getSubject(),null,List.of(new SimpleGrantedAuthority("ROLE_"+cl.get("role",String.class))));
     SecurityContextHolder.getContext().setAuthentication(auth);
   }}
   c.doFilter(r,s);
 }
}
