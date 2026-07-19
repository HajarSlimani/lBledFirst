package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Booking;
import ma.lbledfirst.backend.domain.BookingStatus;
import ma.lbledfirst.backend.domain.Experience;
import ma.lbledfirst.backend.domain.ExperienceStatus;
import ma.lbledfirst.backend.domain.User;
import ma.lbledfirst.backend.repository.BookingRepository;
import ma.lbledfirst.backend.repository.ExperienceRepository;
import ma.lbledfirst.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BookingService extends AbstractCrudService<Booking, Long> {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository repository,
                           ExperienceRepository experienceRepository,
                           UserRepository userRepository) {
        super(repository);
        this.experienceRepository = experienceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Booking save(Booking booking) {
        if (booking.getExperience() == null || booking.getExperience().getId() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Experience is required");
        }
        if (booking.getTourist() == null || booking.getTourist().getId() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Tourist is required");
        }

        // Fetch the REAL, fully-loaded records from the database —
        // never trust the incomplete objects sent by the client
        Experience experience = experienceRepository.findById(booking.getExperience().getId())
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Experience not found"));
        User tourist = userRepository.findById(booking.getTourist().getId())
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Tourist not found"));

        if (experience.getStatus() != ExperienceStatus.published) {
            throw new ResponseStatusException(BAD_REQUEST, "Cannot book an experience that is not published");
        }

        if (experience.getHost() != null && tourist.getId().equals(experience.getHost().getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "A host cannot book their own experience");
        }

        booking.setExperience(experience);
        booking.setTourist(tourist);
        booking.setTotalPrice(experience.getPrice());
        booking.setStatus(BookingStatus.pending);

        return super.save(booking);
    }
}