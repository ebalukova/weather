package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericDao<T extends Serializable, PK extends Serializable> {

    /**
     * Generic method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.
     * 
     * @return List of populated objects
     */
    @Transactional(propagation = Propagation.REQUIRED)
    List<T> getAll();

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * 
     * @param id
     *            the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert. It
     * returns a new object instance in the case, when the object is updated.
     * (It might allow us to overcome the optimistic locking problem).
     * 
     * @param object
     *            the object to save
     * @return - persisted instance of the object (the same when first time
     *         persisted, or a new instance when updated)
     * 
     * @param <S>
     *            Тип результата (может быть потомком T)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    <S extends T> S save(T object);

    /**
     * Generic method to delete an object based on class and id
     * 
     * @param object
     *            an object instance
     */
    // @Transactional(propagation = Propagation.REQUIRED)
    void remove(T object);

    /**
     * Gets all records without duplicates.
     * <p>
     * Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods
     * </p>
     * 
     * @return List of populated objects
     */
    List<T> getAllDistinct();

    /**
     * Find a list of records by using a named query
     * 
     * @param queryName
     *            query name of the named query
     * @param queryParams
     *            an array of param values
     * @return a list of the records found
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * Анонимная функция, находящая объект по идентификатору и произвольному
     * классу.
     * 
     * @param <S>
     *            Тип объекта, к которому требуется привести результат
     * @param id
     *            Уникальный ключ объекта в БД
     * @param clazz
     *            Класс, подлежащий сериализации в БД
     * @return Считанный из БД экземпляр объекта, либо null, если объект не
     *         существует в БД.
     */
    <S extends Serializable> S get(PK id, Class<S> clazz);

    /**
     * Анонимная функция, загружающая все объекты по произвольному классу.
     * 
     * @param <S>
     *            Тип объекта, к которому требуется привести результат
     * @param clazz
     *            Класс, подлежащий сериализации в БД
     * @return Считанный из БД список экземпляров объекта.
     */
    <S extends Serializable> List<S> getAll(Class<S> clazz);

    /**
     * Анонимная функция, удаляющая объект по идентификатору и произвольному
     * классу.
     * 
     * @param <S>
     *            Тип объекта, который надо удалить
     * @param id
     *            Уникальный ключ объекта в БД
     * @param clazz
     *            Класс, подлежащий сериализации в БД
     */
    <S extends Serializable> void remove(PK id, Class<S> clazz);

    /**
     * Возвращает класс персистного объекта
     * 
     * @return класс персистного объекта
     */
    Class<?> getPersistentClass();

    /**
     * Перечитывание объекта из БД
     * 
     * @param object
     *            Персистентный объект
     */
    void refresh(Object object);

}
