package ma.lbledfirst.backend.repository;

import ma.lbledfirst.backend.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
