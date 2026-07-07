package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.User;
import ma.lbledfirst.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCrudService<User, Long> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
