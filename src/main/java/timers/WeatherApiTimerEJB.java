/**
 * 
 */
package timers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import utils.DateUtils;
import utils.ObjectUtils;
import api.AbstractApi;
import api.ApiFormats;
import api.ApiJSON;
import api.ApiXML;
import dao.ApiDao;
import dao.CityInfoDao;
import dao.WeatherInfoDao;
import entity.Api;
import entity.CityInfo;
import entity.DefaultStates;
import entity.WeatherInfo;

/**
 * CheckOrsAtmsTimerEJBImpl class
 * 
 * Таймер для проверки обновления информации о банкоматах орс.
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */
@Stateless
public class WeatherApiTimerEJB {

    /**
     * Логгер
     */
    private final Logger log = Logger.getLogger(WeatherApiTimerEJB.class
            .getName());

    @Inject
    private ApiDao apiDao;

    @Inject
    private CityInfoDao cityDao;

    @Inject
    private WeatherInfoDao weatherDao;

    /**
     * Загружаем информацию по погоде из внешних источников
     */
    @Schedule(minute = "*/1", hour = "*", persistent = false)
    public void check() {

        List<Api> apis = apiDao.getByNameApiByState(DefaultStates.Active);
        List<CityInfo> cities = cityDao.getAll();

        log.log(Level.INFO, "-----> apis and cities are " + apis.size() + " "
                + cities.size());

        for (CityInfo city : cities) {

            for (Api apiInfo : apis) {

                WeatherInfo weather =
                        weatherDao.getByNameWeatherByCityAndDate(city,
                                apiInfo, DateUtils.now());

                if (ObjectUtils.isNull(weather)) {
                    weather = new WeatherInfo();
                }

                String url = apiInfo.getUrl() + city.getName();
                if (ApiFormats.Xml.name().equals(apiInfo.getFormat())) {

                    ApiXML api = new ApiXML(url);
                    persistWeather(api, apiInfo, city, weather);
                } else if (ApiFormats.Json.name().equals(apiInfo.getFormat())) {

                    ApiJSON api = new ApiJSON(url);
                    persistWeather(api, apiInfo, city, weather);
                }
            }
        }

    }

    /**
     * Сохраняем информацию о погоде в бд
     * 
     * @param api
     *            Реализация интерфейса внешнего апи
     * @param apiInfo
     *            Информация об источнике
     * @param cityInfo
     *            Город
     * @param weather
     *            Информация о погоде
     */
    public <T extends AbstractApi> void persistWeather(T api, Api apiInfo,
            CityInfo cityInfo, WeatherInfo weather) {

        Map<String, Object> params = new HashMap<>();
        weather =
                (WeatherInfo) api.parse(apiInfo.getReaderClassName(),
                        api.get(params), weather);

        if (ObjectUtils.notNull(weather)) {

            weather.setApi(apiInfo);
            weather.setCityInfo(cityInfo);
            weatherDao.save(weather);
        }
    }
}
