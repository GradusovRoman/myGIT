package ru.geekbrains.thirdquarter.springintro.springboot.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.thirdquarter.springintro.springboot.app.dao.ARepository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.exceptions.NotFountException;

import java.util.List;

public abstract class AService<R extends ARepository<E, I>, E, I extends Number> {
    protected R repository;

    protected abstract E createEmptyEntity();

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public Page<E> getAll(int pageNumber, int elementsLimit) {
        return repository.findAll(PageRequest.of(pageNumber, elementsLimit));
    }

    public Page<E> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E getOrNew(I id) {
        return (id.longValue() <= 0) ? createEmptyEntity() : repository.findById(id).orElseThrow(NotFountException::new);
    }

    public void delete(I id) {
        repository.deleteById(id);
    }

    public void save(E entity) {
        repository.save(entity);
    }
}
