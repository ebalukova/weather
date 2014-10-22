/**
 * 
 */
package api;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * Интерфейс для доступа к http - методам
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public interface HttpTool {

    /**
     * GET - запрос
     * 
     * @param url
     *            Url запроса
     * @param headers
     *            Дополнительные заголовки
     * @param params
     *            Набор параметров
     * @return Ответ
     */
    ResponseEntity<String> get(String url, final HttpHeaders headers,
            Map<String, ?> params);
}
