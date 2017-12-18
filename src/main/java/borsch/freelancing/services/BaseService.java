package borsch.freelancing.services;

/**
 * Created by Andrii on 25.07.2017.
 */

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.exceptions.service_error.ServiceErrorException;
import borsch.freelancing.convertors.Converter;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.exceptions.not_found.NoSuchEntityException;
import borsch.freelancing.mergers.Merger;
import borsch.freelancing.persistence.criteria.ICriteriaRepository;
import borsch.freelancing.persistence.dao.repositories.BaseRepository;
import borsch.freelancing.pojo.helpers.GetableById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @param <E> Entity class
 * @param <V> View class
 * @param <I> Entity primary key
 */

public abstract class BaseService<E extends GetableById<I>,V extends GetableById<I>,I extends Serializable> implements IService<E,V,I>{

    @Autowired
    private BaseRepository<E,I> repository;
    @Autowired
    private Converter<E> converter;
    @Autowired
    private Merger<E,V> merger;
    @Autowired
    private ICriteriaRepository criteriaRepository;
    @Autowired
    private IValidator<E> validationService;
    private Class<E> persistentClass;


    public BaseService(Class<E> entityClass){
        this.persistentClass = entityClass;
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public E getById(I id) throws BaseException {
        E entity = repository.findOne(id);
        if (entity == null)
            throw new NoSuchEntityException(persistentClass.getName(), "id: " + id);
        validationService.validForView(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Map<String, Object> getById(I id, Set<String> fields) throws BaseException {
        return converter.convert(getById(id),fields);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public List<E> getList(Criteria<E> criteria) throws BaseException {
        List<E> entities = criteriaRepository.find(criteria);
        if (entities == null || entities.isEmpty())
            throw new NoSuchEntityException(persistentClass.getName(), criteria.toString());
        validationService.validForView(entities);
        return entities;
    }

    @Transactional(rollbackFor = BaseException.class)
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        return getList(parse(restrict), fields);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public List<Map<String, Object>> getList(Criteria<E> criteria, Set<String> fields) throws BaseException{
        return converter.convert(getList(criteria),fields);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public I create(V view) throws BaseException, IllegalAccessException, InstantiationException {
        E entity = persistentClass.newInstance();
        merger.merge(entity,view);
        validationService.validForCreate(entity);
        entity = repository.saveAndFlush(entity);
        if(entity==null)
            throw new ServiceErrorException();
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public boolean update(V view) throws BaseException {
        E entity = repository.findOne(view.getId());
        if (entity == null)
            throw new NoSuchEntityException(persistentClass.getName(), "id" + view.getId());
        merger.merge(entity,view);
        validationService.validForUpdate(entity);
        entity = repository.saveAndFlush(entity);
        return entity != null;
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public int count(String restrict) throws WrongRestrictionException {
        return count(parse(restrict));
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public int count(Criteria<E> criteria){
        return criteriaRepository.count(criteria);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public boolean delete(I id) throws BaseException {
        E entity = repository.findOne(id);
        if (entity == null)
            throw new NoSuchEntityException(persistentClass.getName(), "id" + id);
        validationService.validForDelete(entity);
        repository.delete(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public boolean save(E entity){
        entity = repository.saveAndFlush(entity);
        return entity != null;
    }
}
