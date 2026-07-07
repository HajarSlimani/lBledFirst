package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Video;
import ma.lbledfirst.backend.repository.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class VideoService extends AbstractCrudService<Video, Long> {
    public VideoService(VideoRepository repository) {
        super(repository);
    }
}
