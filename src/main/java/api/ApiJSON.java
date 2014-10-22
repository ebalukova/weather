/**
 * 
 */
package api;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import utils.ObjectUtils;
import api.readers.AbstractJsonParser;
import entity.AbstractObject;

/**
 * Пример использования API для JSON - формата.
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public class ApiJSON extends AbstractApi {

    /**
     * Конструктор сервиса
     * 
     * @param url
     *            Url запроса
     */
    public ApiJSON(String url) {

        super(url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareHeaders(HttpHeaders headers) {

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AbstractObject> T parse(String className, String input,
            T resultObj) {

        return parseJson(className, input, resultObj);
    }

    /**
     * Адаптация парсинга для JSON формата
     * 
     * @param className
     *            Имя класса ридера, который будет обрабатывать данные
     * @param input
     *            Данные которые надо обработать
     * @return Результат обработки
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractJsonParser<S>, S extends AbstractObject> S parseJson(
            String className, String input, S resultObj) {

        S result = resultObj;
        try {
            T parser = (T) ObjectUtils.getInstance(className);
            result = parser.parse(input, resultObj);
        } catch (Exception ex) {
            result = null;
            // throw new ApiException("Api exception", ex);
        }
        return result;
    }
}
