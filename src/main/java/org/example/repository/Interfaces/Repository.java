package org.example.repository.Interfaces;

import java.util.List;
import java.util.Optional;

public interface Repository<E, I> {
    E insert(E entity);

    void update(E entity);

    boolean deleteById(I id);

    Optional<E> findById(I id);

    boolean exitsById(I id);
}
