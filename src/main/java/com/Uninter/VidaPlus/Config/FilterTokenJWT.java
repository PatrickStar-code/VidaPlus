package com.Uninter.VidaPlus.Config;

import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import com.Uninter.VidaPlus.Repository.UserSystemRepository;
import com.Uninter.VidaPlus.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterTokenJWT extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserSystemRepository userSystemRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenRequisition(request);
            if (token != null) {
                String login = tokenService.verifyToken(token);
                UserSystemEntity user = userSystemRepository.findByLoginIgnoreCase(login)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();

            if (!response.isCommitted()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                String json = "{\"error\": \"" + ex.getMessage().replace("\"", "'") + "\"}";
                response.getWriter().write(json);
                response.getWriter().flush();
            }
            System.out.println("Erro no filtro JWT: " + ex.getMessage());
            return;
        }
        System.out.println("Chamando filterChain.doFilter");
        filterChain.doFilter(request, response);
        System.out.println("Depois do filterChain.doFilter");

    }


    private String getTokenRequisition(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
