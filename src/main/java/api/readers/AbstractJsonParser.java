/**
 * 
 */
package api.readers;

import entity.AbstractObject;

/**
 * Интерфейс для JSON парсеров.
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public interface AbstractJsonParser<T extends AbstractObject> {

    /**
     * JSON-специфичный парсинг
     * 
     * @param input
     *            Данные которые надо обработать
     * @param resultObj
     *            Объект в который нужно записать результат
     * @return Результат обработки
     */
    public T parse(String input, T resultObj);
}
