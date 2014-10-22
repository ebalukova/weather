/**
 * 
 */
package api;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import entity.AbstractObject;

/**
 * Сервис, делающий запросы во внешнее апи
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public abstract class AbstractApi {

    /**
     * Сервис для http-транспорта
     */
    private final HttpTool http;

    /**
     * Url запроса
     */
    private final String url;

    /**
     * Конструктор сервиса
     * 
     * @param url
     *            Url запроса
     */
    public AbstractApi(String url) {

        this.url = url;

        this.http = new HttpToolImpl(url);

        RestTemplate template = new RestTemplate();

        ((HttpToolImpl) http).setRestTemplate(template);
    }

    /**
     * Получение данных через GET - вариант
     * 
     * @param params
     *            Набор параметров запроса
     * @return Ответ
     */
    public String get(Map<String, ?> params) {

        HttpHeaders headers = new HttpHeaders();
        prepareHeaders(headers);

        ResponseEntity<String> r = http.get(url, headers, params);
        return r.getBody();
    }

    /**
     * Парсингом результата, конкретным обработчиком
     * 
     * @param className
     *            Имя класса ридера, который будет обрабатывать данные
     * @param input
     *            Данные которые надо обработать
     * @param resultObj
     *            Объект в который нужно записать результат
     * @return Результат обработки
     */
    public abstract <S extends AbstractObject> S parse(String className,
            String input, S resultObj);

    /**
     * Дополнительная работы с заголовками
     * 
     * @param headers
     *            Набор заголовков
     */
    protected void prepareHeaders(HttpHeaders headers) {

        /*
         * Empty
         */
    }
}
