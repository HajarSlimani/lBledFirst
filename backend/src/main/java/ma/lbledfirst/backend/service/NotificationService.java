package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Notification;
import ma.lbledfirst.backend.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends AbstractCrudService<Notification, Long> {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Notification save(Notification notification) {
        notification.setIsRead(false);
        return super.save(notification);
    }

    public Notification markAsRead(Long id) {
        Notification notification = findById(id);
        notification.setIsRead(true);
        return repository.save(notification);
    }
}