package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Review;
import ma.lbledfirst.backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractCrudService<Review, Long> {
    public ReviewService(ReviewRepository repository) {
        super(repository);
    }
}
