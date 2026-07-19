package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Video;
import ma.lbledfirst.backend.domain.VideoStatus;
import ma.lbledfirst.backend.repository.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class VideoService extends AbstractCrudService<Video, Long> {

    public VideoService(VideoRepository repository) {
        super(repository);
    }

    @Override
    public Video save(Video video) {
        // A newly uploaded video always starts in draft,
        // it becomes "processing" once the AI pipeline actually starts
        video.setStatus(VideoStatus.draft);
        return super.save(video);
    }
}