# cwbusinesservices

1. java 8
1. database name `cwbusinesservices`

Process of creating new api controller:
1. Create db migration file (in name should be numeration and __). Don't forget about permissions (in sql and in `PermissionsEnum`).
2. Create entity class that extends `CompareIntegerId` (abstract class that used in basic validation realization), implement `GetableById` with id type parametr (used in basic repository realization)
3. Create view class that extends `CompareIntegerId` (abstract class that used in basic validation realization), implement `GetableById` with id type parametr (used in basic repository realization)
4. Create criteria class  that extends typed abstract class `Criteria` with parametr Entity class. You should add fields that can be used for query builder and method query.
5. Add static class to class `Fields` tha describe fields that will be used on frontend
6. Create class converter that convert `Objec` to `Map` of fields. It should extend `Converter` with type of entity and have annotation `@Component`
7. Create interface that extends `BaseRepository` with type of entity. And you can add methods that you need according to Spring DATA specification
8. Create merger class that implements `Merger` typed with Entity and View class. It should be annotated with `@Component`. We need merger to copy values from entity to view and backward
9. Create abstract service that extends `BaseService` if Entity does not have file or `FileWorkBaseService` in other case. This two abstract clases has all needed methods.
10. Create validation interface that extends `IValidator`. Validator has methods that we need for entity validation.
11. Create validation interface realization that extends `BaseValidator` and implements your validation service interface and overwrite methods if need. It should have annotation `@Service`
12. Create service realization with two annotations `@Service` and `@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)`
13. Create RESTful API controller class with annotations `@Controller` and `@RequestMapping` and extends `BaseApiController`
14. If you want add your custom exception then it should extends `BaseException` class. This exception implements basic functionality to convert server exceptions to readable exceptions for web part


