package ma.lbledfirst.backend.controller;

import lombok.RequiredArgsConstructor;
import ma.lbledfirst.backend.domain.User;
import ma.lbledfirst.backend.domain.UserRole;
import ma.lbledfirst.backend.repository.UserRepository;
import ma.lbledfirst.backend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        if (userRepository.findByEmail(body.get("email")).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email déjà utilisé"));
        }
        User user = User.builder()
                .name(body.get("name"))
                .email(body.get("email"))
                .password(passwordEncoder.encode(body.get("password")))
                .role(UserRole.valueOf(body.getOrDefault("role", "tourist")))
                .country(body.get("country"))
                .language(body.get("language"))
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return ResponseEntity.ok(Map.of("token", token, "role", user.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        return userRepository.findByEmail(body.get("email"))
                .filter(u -> passwordEncoder.matches(body.get("password"), u.getPassword()))
                .map(u -> ResponseEntity.ok(Map.of(
                        "token", jwtUtil.generateToken(u.getEmail(), u.getRole().name()),
                        "role", u.getRole(),
                        "name", u.getName())))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Identifiants invalides")));
    }
}