package ma.lbledfirst.backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/api/test/public")
    public Map<String, String> publicEndpoint() {
        return Map.of("message", "Route publique, accessible sans token");
    }

    @GetMapping("/api/test/protected")
    public Map<String, String> protectedEndpoint(Authentication authentication) {
        return Map.of(
                "message", "Route protégée, tu es bien authentifié !",
                "email", authentication.getName()
        );
    }
}