package borsch.freelancing.persistence.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface BaseRepository<E,I extends Serializable> extends JpaRepository<E,I> {
    List<E> findAll();
    List<E> findAll(Sort sort);
    Page<E> findAll(Pageable pageable);
}
