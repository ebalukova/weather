/**
 * 
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.Api;
import entity.DefaultStates;

/**
 * ApiDaoImpl class
 * 
 * DAO для работы с Api.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */

@Stateless
@Local(ApiDao.class)
public class ApiDaoImpl extends AbstractDaoImpl<Api> implements ApiDao {

    /**
     * Конструктор по умолчанию
     */
    public ApiDaoImpl() {

        super(Api.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Api> getByNameApiByState(DefaultStates state) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", state.name());

        return findByNamedQuery("apiByState", params);
    }
}
