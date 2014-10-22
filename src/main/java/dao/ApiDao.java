/**
 * 
 */
package dao;

import java.util.List;

import entity.Api;
import entity.DefaultStates;

/**
 * ApiDao class
 * 
 * DAO - интерфейс для использования Api.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */

public interface ApiDao extends AbstractDao<Api> {

    /**
     * Получаем список источников по статусу
     * 
     * @param state
     *            Статус
     * @return Список источников
     */
    public List<Api> getByNameApiByState(DefaultStates state);
}
