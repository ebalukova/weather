/**
 * 
 */
package dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.CityInfo;

/**
 * CityInfoDaoImpl class
 * 
 * DAO для работы с CityInfo.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */

@Stateless
@Local(CityInfoDao.class)
public class CityInfoDaoImpl extends AbstractDaoImpl<CityInfo> implements
        CityInfoDao {

    /**
     * Конструктор по умолчанию
     */
    public CityInfoDaoImpl() {

        super(CityInfo.class);
    }

}
