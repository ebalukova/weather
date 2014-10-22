/**
 *
 */
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WeatherInfo class
 * 
 * Объект хранящий информацию о городе
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */
@Entity
@Table(name = "city_info")
public class CityInfo extends AbstractModifiableObject implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 177204291008026845L;

    /**
     * Имя города
     */
    private String name;

    /**
     * Страна
     */
    private String country;

    /**
     * Двухбуквенный код страны
     */
    private String countryCode;

    /**
     * Идентификатор города в международной системе geonames
     */
    private String geoNameId;

    // ////////////////////////////////////////////////////////////////////////
    // //// getters/setters
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @return the name
     */
    @Column(name = "name")
    public String getName() {

        return name;
    }

    /**
     * @return the country
     */
    @Column(name = "country")
    public String getCountry() {

        return country;
    }

    /**
     * @return the countryCode
     */
    @Column(name = "country_code")
    public String getCountryCode() {

        return countryCode;
    }

    /**
     * @return the geoNameId
     */
    @Column(name = "geo_name_id")
    public String getGeoNameId() {

        return geoNameId;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {

        this.country = country;
    }

    /**
     * @param countryCode
     *            the countryCode to set
     */
    public void setCountryCode(String countryCode) {

        this.countryCode = countryCode;
    }

    /**
     * @param geoNameId
     *            the geoNameId to set
     */
    public void setGeoNameId(String geoNameId) {

        this.geoNameId = geoNameId;
    }

}
