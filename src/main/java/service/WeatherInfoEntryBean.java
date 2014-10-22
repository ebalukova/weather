package service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.WeatherInfoDao;
import entity.WeatherInfo;

/**
 * 
 * WeatherInfoEntryBean class
 * 
 * Сервис для отображения информации о погоде в вебе
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

@Named(value = "weatherInfoEntryBean")
@RequestScoped
public class WeatherInfoEntryBean {

    @Inject
    private WeatherInfoDao dao;

    /**
     * @return the entity
     */
    public List<WeatherInfo> getEntities() {

        return dao.getAll();
    }
}
