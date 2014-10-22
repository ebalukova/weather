/**
 *
 */
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * WeatherInfo class
 * 
 * Объект хранящий информацию о погоде
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */
@Entity
@Table(name = "weather_info", indexes = { @Index(name = "idx_city_api_date", columnList = "ID_CITY, ID_API, I_SORT_DATE") })
@NamedQueries({ @NamedQuery(name = "weatherByCityAndDate", query = "select w from WeatherInfo w where "
        + "w.cityInfo = :cityInfo and w.api = :api and w.sortDate=:sortDate order by w.sortDate desc") })
public class WeatherInfo extends AbstractModifiableObject implements
        Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 177204291008026846L;

    /**
     * Город
     */
    private CityInfo cityInfo;

    /**
     * Источник
     */
    private Api api;

    /**
     * Максимальная температура (С)
     */
    private Integer maxT;

    /**
     * Минимальная температура (С)
     */
    private Integer minT;

    /**
     * Текущая температура (С)
     */
    private Integer curT;

    /**
     * Влажность (%)
     */
    private String humidity;

    /**
     * Скорость ветра (м/с)
     */
    private Double windSpeed;

    /**
     * Направление ветра
     */
    private String windDirection;

    /**
     * Осадки (мм)
     */
    private String precipitation;

    /**
     * Давление (Па)
     */
    private String pressure;

    /**
     * Описание
     */
    private String description;

    // ////////////////////////////////////////////////////////////////////////
    // //// getters/setters
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @return the cityInfo
     */
    @ManyToOne
    @JoinColumn(name = "id_city")
    public CityInfo getCityInfo() {

        return cityInfo;
    }

    /**
     * @return the api
     */
    @ManyToOne
    @JoinColumn(name = "id_api")
    public Api getApi() {

        return api;
    }

    /**
     * @return the maxT
     */
    @Column(name = "max_t")
    public Integer getMaxT() {

        return maxT;
    }

    /**
     * @return the minT
     */
    @Column(name = "min_t")
    public Integer getMinT() {

        return minT;
    }

    /**
     * @return the curT
     */
    @Column(name = "cur_t")
    public Integer getCurT() {

        return curT;
    }

    /**
     * @return the humidity
     */
    @Column(name = "humidity")
    public String getHumidity() {

        return humidity;
    }

    /**
     * @return the windSpeed
     */
    @Column(name = "wind_speed")
    public Double getWindSpeed() {

        return windSpeed;
    }

    /**
     * @return the windDirection
     */
    @Column(name = "wind_direction")
    public String getWindDirection() {

        return windDirection;
    }

    /**
     * @return the precipitation
     */
    @Column(name = "precipitation")
    public String getPrecipitation() {

        return precipitation;
    }

    /**
     * @return the pressure
     */
    @Column(name = "pressure")
    public String getPressure() {

        return pressure;
    }

    /**
     * @return the description
     */
    @Column(name = "description")
    public String getDescription() {

        return description;
    }

    /**
     * @param cityInfo
     *            the cityInfo to set
     */
    public void setCityInfo(CityInfo cityInfo) {

        this.cityInfo = cityInfo;
    }

    /**
     * @param api
     *            the api to set
     */
    public void setApi(Api api) {

        this.api = api;
    }

    /**
     * @param maxT
     *            the maxT to set
     */
    public void setMaxT(Integer maxT) {

        this.maxT = maxT;
    }

    /**
     * @param minT
     *            the minT to set
     */
    public void setMinT(Integer minT) {

        this.minT = minT;
    }

    /**
     * @param curT
     *            the curT to set
     */
    public void setCurT(Integer curT) {

        this.curT = curT;
    }

    /**
     * @param humidity
     *            the humidity to set
     */
    public void setHumidity(String humidity) {

        this.humidity = humidity;
    }

    /**
     * @param windSpeed
     *            the windSpeed to set
     */
    public void setWindSpeed(Double windSpeed) {

        this.windSpeed = windSpeed;
    }

    /**
     * @param windDirection
     *            the windDirection to set
     */
    public void setWindDirection(String windDirection) {

        this.windDirection = windDirection;
    }

    /**
     * @param precipitation
     *            the precipitation to set
     */
    public void setPrecipitation(String precipitation) {

        this.precipitation = precipitation;
    }

    /**
     * @param pressure
     *            the pressure to set
     */
    public void setPressure(String pressure) {

        this.pressure = pressure;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {

        this.description = description;
    }

}
