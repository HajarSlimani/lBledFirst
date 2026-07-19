package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Experience;
import ma.lbledfirst.backend.domain.ExperienceStatus;
import ma.lbledfirst.backend.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService extends AbstractCrudService<Experience, Long> {

    public ExperienceService(ExperienceRepository repository) {
        super(repository);
    }

    @Override
    public Experience save(Experience experience) {
        // New experiences always start as draft, regardless of client input
        experience.setStatus(ExperienceStatus.draft);
        return super.save(experience);
    }

    public Experience publish(Long id) {
        Experience experience = findById(id);
        experience.setStatus(ExperienceStatus.published);
        return super.save(experience);
    }
}