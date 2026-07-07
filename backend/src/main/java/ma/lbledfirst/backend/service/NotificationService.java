package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Notification;
import ma.lbledfirst.backend.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends AbstractCrudService<Notification, Long> {
    public NotificationService(NotificationRepository repository) {
        super(repository);
    }
}
