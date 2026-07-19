package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Message;
import ma.lbledfirst.backend.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class MessageService extends AbstractCrudService<Message, Long> {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Message save(Message message) {
        if (message.getSender() == null || message.getSender().getId() == null
                || message.getReceiver() == null || message.getReceiver().getId() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Sender and receiver are required");
        }

        if (message.getSender().getId().equals(message.getReceiver().getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "Cannot send a message to yourself");
        }

        message.setIsRead(false);
        return super.save(message);
    }

    public Message markAsRead(Long id) {
        Message message = findById(id);
        message.setIsRead(true);
        return repository.save(message);
    }
}