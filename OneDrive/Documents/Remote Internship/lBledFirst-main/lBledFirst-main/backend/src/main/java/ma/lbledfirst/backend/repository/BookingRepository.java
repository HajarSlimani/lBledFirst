package ma.lbledfirst.backend.repository;

import ma.lbledfirst.backend.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
