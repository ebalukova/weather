/**
 * 
 */
package dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import utils.DateUtils;
import entity.Api;
import entity.CityInfo;
import entity.WeatherInfo;

/**
 * WeatherInfoDaoImpl class
 * 
 * DAO для работы с WeatherInfo.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */

@Stateless
@Local(WeatherInfoDao.class)
public class WeatherInfoDaoImpl extends AbstractDaoImpl<WeatherInfo> implements
        WeatherInfoDao {

    /**
     * Конструктор по умолчанию
     */
    public WeatherInfoDaoImpl() {

        super(WeatherInfo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherInfo getByNameWeatherByCityAndDate(CityInfo city, Api api,
            Date date) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cityInfo", city);
        params.put("api", api);
        params.put("sortDate", DateUtils.beginningOfDay(date));

        List<WeatherInfo> weathers =
                findByNamedQuery("weatherByCityAndDate", params);

        return weathers.isEmpty() ? null : weathers.get(0);
    }
}
