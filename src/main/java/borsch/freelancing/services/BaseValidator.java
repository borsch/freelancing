package borsch.freelancing.services;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.service_error.ForbiddenException;
import borsch.freelancing.exceptions.service_error.ValidationException;
import borsch.freelancing.pojo.helpers.GetableById;
import borsch.freelancing.exceptions.conflict.EntityValidateException;
import borsch.freelancing.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class BaseValidator<E extends GetableById<I>, I> implements IValidator<E>{
    @Autowired
    private Validator validator;

    private Class<E> persistentClass;

    public BaseValidator(Class<E> persistentClass){
        this.persistentClass = persistentClass;
    }

    @Override
    public void validForCreate(E entity) throws BaseException {
        if (entity.compareId(0) > 0)
            throw new EntityValidateException("errors.EntityCreateException.id.create");
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(persistentClass.getName(), violations);
        }
    }

    @Override
    public void validForUpdate(E entity) throws BaseException {
        if (entity.compareId(0) == 0)
            throw new EntityValidateException("errors.EntityCreateException.id.update");

        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(persistentClass.getName(), violations);
        }
    }

    @Override
    public void validForView(E entity) throws BaseException {

    }

    @Override
    public void validForView(List<E> entities) throws BaseException {
        for (E entity : entities)
            validForView(entity);
    }

    @Override
    public void validForDelete(E entity) throws BaseException {

    }

}
