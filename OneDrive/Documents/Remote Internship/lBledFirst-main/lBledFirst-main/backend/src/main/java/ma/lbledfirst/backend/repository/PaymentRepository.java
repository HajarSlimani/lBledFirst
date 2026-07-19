package ma.lbledfirst.backend.repository;

import java.util.Optional;

import ma.lbledfirst.backend.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBookingId(Long bookingId);
}
