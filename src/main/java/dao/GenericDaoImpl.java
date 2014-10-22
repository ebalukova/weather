package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import utils.ObjectUtils;

/**
 * 
 * GenericDaoImpl class
 * 
 * Реализация Dao общего вида.
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 * @param <T>
 *            Тип объекта
 * @param <PK>
 *            Тип первичного ключа
 */

public class GenericDaoImpl<T extends Serializable, PK extends Serializable>
        implements GenericDao<T, PK> {

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Конкретный DAO должен определять данное свойство, поскольку фактически по
     * нему идет определение типа.
     */
    private final Class<T> persistentClass;

    /**
     * Через данный конструктор выставляется класс, с которым работает данное
     * DAO
     * 
     * @param persistentClass
     *            Класс, ассоциированный с данным DAO
     */
    public GenericDaoImpl(Class<T> persistentClass) {

        super();
        this.persistentClass = persistentClass;
    }

    /**
     * default-конструктор
     */
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {

        this((Class<T>) Serializable.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(PK id) {

        T value = entityManager.find(persistentClass, id);
        return ObjectUtils.notNull(value);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> queryParams) {

        Set<Map.Entry<String, Object>> rawParameters = queryParams.entrySet();
        Query query =
                entityManager.createNamedQuery(namedQueryName,
                        this.persistentClass);

        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(PK id) {

        return getByClass(id, persistentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Serializable> List<S> getAll(Class<S> clazz) {

        return entityManager.createQuery(
                "SELECT e FROM " + clazz.getSimpleName() + " e", clazz)
                .getResultList();
    }

    /**
     * Чтение объекта по идентификатору и заданному классу
     * 
     * @param <S>
     *            Тип объекта,к которому приводится результат
     * @param id
     *            Уникальный ключ объекта в БД
     * @param clazz
     *            Класс объекта
     * @return Экземпляр объекта
     */
    private <S extends Serializable> S getByClass(PK id, Class<S> clazz) {

        return entityManager.find(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {

        return entityManager
                .createQuery(
                        "SELECT e FROM "
                                + this.persistentClass.getSimpleName() + " e",
                        this.persistentClass).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAllDistinct() {

        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T object) {

        entityManager.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <S extends T> S save(T object) {

        T result = object;

        if (entityManager.contains(object)) {

            result = entityManager.merge(object);
        } else {
            entityManager.persist(object);
        }
        return (S) result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Serializable> S get(PK id, Class<S> clazz) {

        return getByClass(id, clazz);
    }

    /**
     * Удаление объекта по идентификатору и заданному классу
     * 
     * @param <S>
     *            Тип объекта,который удаляется
     * @param id
     *            Уникальный ключ объекта в БД
     * @param clazz
     *            Класс объекта
     */
    @Override
    public <S extends Serializable> void remove(PK id, Class<S> clazz) {

        entityManager.remove(getByClass(id, clazz));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(Object object) {

        entityManager.refresh(object);
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getPersistentClass() {

        return persistentClass;
    }
}
