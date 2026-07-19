package ma.lbledfirst.backend.repository;

import java.util.Optional;

import ma.lbledfirst.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
