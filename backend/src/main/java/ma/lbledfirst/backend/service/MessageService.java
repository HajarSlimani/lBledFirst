package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Message;
import ma.lbledfirst.backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends AbstractCrudService<Message, Long> {
    public MessageService(MessageRepository repository) {
        super(repository);
    }
}
