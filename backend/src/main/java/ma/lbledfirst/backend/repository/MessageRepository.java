package ma.lbledfirst.backend.repository;

import ma.lbledfirst.backend.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
