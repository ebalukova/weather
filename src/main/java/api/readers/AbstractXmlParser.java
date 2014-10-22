/**
 * 
 */
package api.readers;

import org.xml.sax.helpers.DefaultHandler;

import entity.AbstractObject;

/**
 * Абстрактный класс для XML парсеров.
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public abstract class AbstractXmlParser<T extends AbstractObject> extends
        DefaultHandler {

    /**
     * Получение результата работы парсера
     * 
     * @return Обработанные данные в виде объекта
     */
    public abstract T getResult();

    /**
     * Устанавливаем объект, в который будем сохранять результаты
     */
    public abstract void setObject(T object);

}
