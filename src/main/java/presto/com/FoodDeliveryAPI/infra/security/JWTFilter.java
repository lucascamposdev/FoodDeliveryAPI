package presto.com.FoodDeliveryAPI.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import presto.com.FoodDeliveryAPI.infra.exceptions.ApiErrorMessage;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidTokenException;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.repository.UserRepository;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CredentialsRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            try{
                String subject = this.tokenService.validateToken(token);
                UserDetails credentials = this.repository.findByEmail(subject);

                var authentication = new UsernamePasswordAuthenticationToken(credentials, null, credentials.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            catch (NullPointerException | InvalidTokenException ex){
                sendErrorResponse(response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest req){
        var authHeader = req.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    private void sendErrorResponse (HttpServletResponse response) throws IOException {
        var pb = ProblemDetail.forStatus(HttpServletResponse.SC_UNAUTHORIZED);
        pb.setTitle("Unauthorized");
        pb.setDetail("Invalid token.");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        objectMapper.writeValue(response.getWriter(), pb);
    }
}

