/**
 *
 */
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * WeatherInfo class
 * 
 * Объект хранящий информацию о сторонних API
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */
@Entity
@Table(name = "api", indexes = { @Index(name = "idx_state", columnList = "STATE") })
@NamedQueries({ @NamedQuery(name = "apiByState", query = "select a from Api a where "
        + "a.state = :state") })
public class Api extends AbstractStateableObject<DefaultStates> implements
        Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -3838427526119810762L;

    /**
     * Url стороннего сервиса
     */
    private String url;

    /**
     * Имя класса - парсера данных из этого источника
     */
    private String className;

    /*
     * Не получилось разобраться как в apache derby вставить VARCHAR FOR BIT
     * DATA из файла data.sql
     */
    // private ApiFormats format;
    /**
     * Формат в котором получаем результаты из API
     */
    private String format;

    /**
     * Имя API
     */
    private String name;

    // ////////////////////////////////////////////////////////////////////////
    // //// getters/setters
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @return the url
     */
    @Column(name = "url")
    public String getUrl() {

        return url;
    }

    /**
     * @return the className
     */
    @Column(name = "class_name")
    public String getReaderClassName() {

        return className;
    }

    /**
     * @return the format
     */
    @Column(name = "format")
    // @Enumerated(EnumType.STRING)
    public String getFormat() {

        return format;
    }

    /**
     * @return the name
     */
    @Column(name = "name")
    public String getName() {

        return name;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {

        this.url = url;
    }

    /**
     * @param className
     *            the className to set
     */
    public void setReaderClassName(String className) {

        this.className = className;
    }

    /**
     * @param format
     *            the format to set
     */
    public void setFormat(String format) {

        this.format = format;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

}
