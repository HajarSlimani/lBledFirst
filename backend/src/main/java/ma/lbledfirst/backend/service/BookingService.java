package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Booking;
import ma.lbledfirst.backend.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService extends AbstractCrudService<Booking, Long> {
    public BookingService(BookingRepository repository) {
        super(repository);
    }
}
