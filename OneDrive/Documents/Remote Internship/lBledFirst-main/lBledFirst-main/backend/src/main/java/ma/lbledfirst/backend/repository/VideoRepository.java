package ma.lbledfirst.backend.repository;

import ma.lbledfirst.backend.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
