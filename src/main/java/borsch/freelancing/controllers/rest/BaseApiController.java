package borsch.freelancing.controllers.rest;

import borsch.freelancing.convertors.Fields;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.helpers.GetableById;
import borsch.freelancing.pojo.response.ResponseFactory;
import borsch.freelancing.services.IService;
import borsch.freelancing.pojo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oleh on 25.07.2017.
 */
public abstract class BaseApiController<E extends GetableById<I>, V extends GetableById<I>, I extends Serializable> {

    @Autowired
    IService<E, V, I> service;

    @Autowired
    ResponseFactory responseFactory;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public
    @ResponseBody
    Response<Map<String, Object>>
    get(
            @PathVariable("id") I id,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(service.getById(id, fields));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public @ResponseBody Response<List<Map<String, Object>>>
    getList(
            @RequestParam(value = "fields", required = false, defaultValue = Fields.DEFAULT) Set<String> fields,
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return responseFactory.get(service.getList(fields, restrict));
    }

    @RequestMapping(
            value = "/count",
            method = RequestMethod.GET
    )
    public @ResponseBody Response<Integer>
    count(
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return responseFactory.get(service.count(restrict));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    public
    @ResponseBody Response<I>
    create(
            @RequestBody V view
    ) throws BaseException, InstantiationException, IllegalAccessException {
        return responseFactory.get(service.create(view));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    public
    @ResponseBody Response<Boolean>
    update(
            @RequestBody V view
    ) throws BaseException {
        return responseFactory.get(service.update(view));
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public
    @ResponseBody Response<Boolean>
    delete(
            @PathVariable("id") I id
    ) throws BaseException {
        return responseFactory.get(service.delete(id));
    }
}
