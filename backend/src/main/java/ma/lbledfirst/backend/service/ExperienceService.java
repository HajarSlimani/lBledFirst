package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Experience;
import ma.lbledfirst.backend.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService extends AbstractCrudService<Experience, Long> {
    public ExperienceService(ExperienceRepository repository) {
        super(repository);
    }
}
