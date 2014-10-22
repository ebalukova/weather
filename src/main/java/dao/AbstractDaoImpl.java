/**
 * 
 */
package dao;

import entity.AbstractObject;

/**
 * AbstractDaoImpl class
 * 
 * Абстрактное DAO для работы с объектами, являющимися потомками от
 * AbstractObject и использующими Long в качестве первичного ключа.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 * @param <T>
 *            Тип объекта
 */

public class AbstractDaoImpl<T extends AbstractObject> extends
        GenericDaoImpl<T, Long> implements AbstractDao<T> {

    /**
     * Конструктор без параметров
     */
    @SuppressWarnings("unchecked")
    public AbstractDaoImpl() {

        super((Class<T>) AbstractObject.class);
    }

    /**
     * Транслируем конструктор предка
     * 
     * @param persistentClass
     *            Персистентный класс
     */
    public AbstractDaoImpl(Class<T> persistentClass) {

        super(persistentClass);
    }
}
