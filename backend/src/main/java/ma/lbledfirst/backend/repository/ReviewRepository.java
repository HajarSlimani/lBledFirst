package ma.lbledfirst.backend.repository;

import ma.lbledfirst.backend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
