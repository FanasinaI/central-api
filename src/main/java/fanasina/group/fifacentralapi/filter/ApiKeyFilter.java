package fanasina.group.fifacentralapi.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class ApiKeyFilter implements Filter {

    @Value("${api.key}")
    private String validApiKey;

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Vérifier si la route nécessite une authentification
        if (shouldAuthenticate(httpRequest)) {
            String apiKey = httpRequest.getHeader(apiKeyHeader);

            if (apiKey == null || !apiKey.equals(validApiKey)) {
                httpResponse.setContentType("application/json");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("{\"error\": \"Invalid or missing API key\"}");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean shouldAuthenticate(HttpServletRequest request) {
        String path = request.getRequestURI();
        // Exclure les endpoints publics si nécessaire
        return path.startsWith("/api/");
    }
}