package borsch.freelancing.services;

import borsch.freelancing.exceptions.BaseException;

import java.util.List;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface IValidator<E> {
    void validForCreate(E entity) throws BaseException;
    void validForUpdate(E entity) throws BaseException;
    void validForView(E entity) throws BaseException;
    void validForView(List<E> entities) throws BaseException;
    void validForDelete(E entity) throws BaseException;
}
