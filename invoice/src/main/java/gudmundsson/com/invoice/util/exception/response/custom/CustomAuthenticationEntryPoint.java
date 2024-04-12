package gudmundsson.com.invoice.util.exception.response.custom;

import java.io.IOException;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	final static Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        logger.warn("CustomAuthenticationEntryPoint.class -> ({}): {}.", request.getRequestURI(),
                authException.getMessage());
        // logger.warn("CustomAuthenticationEntryPoint.class -> (full): ", ex);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getOutputStream().println(
                objectMapper.writeValueAsString(new XErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized.....")));
    }
}
