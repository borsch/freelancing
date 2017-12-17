package borsch.freelancing.mergers;

import borsch.freelancing.exceptions.BaseException;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface Merger<E,V> {
    void merge (E entity, V view) throws BaseException;
}
