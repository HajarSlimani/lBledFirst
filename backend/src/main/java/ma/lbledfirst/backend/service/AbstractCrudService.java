package ma.lbledfirst.backend.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> {

    private final JpaRepository<T, ID> repository;

    protected AbstractCrudService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Resource not found"));
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Resource not found");
        }
        return repository.save(entity);
    }

    @Override
    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Resource not found");
        }
        repository.deleteById(id);
    }
}
