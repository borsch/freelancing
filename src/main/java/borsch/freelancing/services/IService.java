package borsch.freelancing.services;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */

/**
 *
 * @param <E> Entity class
 * @param <V> View class
 * @param <I> Entity primary key
 */
public interface IService<E,V,I extends Serializable> {
    E getById(I id) throws BaseException;
    Map<String, Object> getById(I id, Set<String> fields) throws BaseException;
    List<E> getList(Criteria<E> criteria) throws BaseException;
    List<Map<String, Object>> getList(Criteria<E> criteria, Set<String> fields) throws BaseException;
    List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException;
    I create(V view) throws BaseException, IllegalAccessException, InstantiationException;
    boolean update(V view) throws BaseException;
    int count(String restrict) throws WrongRestrictionException;
    boolean delete(I id) throws BaseException;
    int count(Criteria<E> criteria);
    boolean save(E entity);
    Criteria<E> parse(String restrict) throws WrongRestrictionException;
}
