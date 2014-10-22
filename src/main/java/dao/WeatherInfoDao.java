/**
 * 
 */
package dao;

import java.util.Date;

import entity.Api;
import entity.CityInfo;
import entity.WeatherInfo;

/**
 * WeatherInfoDao class
 * 
 * DAO - интерфейс для использования WeatherInfo.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */

public interface WeatherInfoDao extends AbstractDao<WeatherInfo> {

    /**
     * По городу источнику и дате находим информацию о погоде
     * 
     * @param city
     *            Город
     * @param api
     *            Источник
     * @param date
     *            Дата
     * @return Информация о погоде
     */
    public WeatherInfo getByNameWeatherByCityAndDate(CityInfo city, Api api,
            Date date);
}
