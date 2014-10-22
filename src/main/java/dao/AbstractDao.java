/**
 * 
 */
package dao;

import entity.AbstractObject;

/**
 * AbstractDao class
 * 
 * DAO - интерфейс для использования AbstractObject.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 * @param <T>
 *            Тип абстрактного объекта
 */

public interface AbstractDao<T extends AbstractObject> extends
        GenericDao<T, Long> {

}
