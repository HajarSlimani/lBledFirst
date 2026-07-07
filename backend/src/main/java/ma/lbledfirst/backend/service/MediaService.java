package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Media;
import ma.lbledfirst.backend.repository.MediaRepository;
import org.springframework.stereotype.Service;

@Service
public class MediaService extends AbstractCrudService<Media, Long> {
    public MediaService(MediaRepository repository) {
        super(repository);
    }
}
