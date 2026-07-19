package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Booking;
import ma.lbledfirst.backend.domain.BookingStatus;
import ma.lbledfirst.backend.domain.Review;
import ma.lbledfirst.backend.repository.BookingRepository;
import ma.lbledfirst.backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ReviewService extends AbstractCrudService<Review, Long> {

    private final BookingRepository bookingRepository;

    public ReviewService(ReviewRepository repository, BookingRepository bookingRepository) {
        super(repository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review save(Review review) {
        if (review.getTourist() == null || review.getTourist().getId() == null
                || review.getExperience() == null || review.getExperience().getId() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Tourist and experience are required");
        }

        List<Booking> bookings = bookingRepository.findAll();
        boolean hasCompletedBooking = bookings.stream()
                .anyMatch(b -> b.getTourist().getId().equals(review.getTourist().getId())
                        && b.getExperience().getId().equals(review.getExperience().getId())
                        && b.getStatus() == BookingStatus.completed);

        if (!hasCompletedBooking) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "You can only review an experience you have completed");
        }

        return super.save(review);
    }
}