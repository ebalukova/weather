/**
 * 
 */
package api;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import javax.xml.parsers.SAXParserFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import utils.StringUtils;
import api.readers.AbstractXmlParser;
import entity.AbstractObject;

/**
 * Пример использования API для XML - формата. Тот или иной формат ответа (JSON
 * или XML) определяется заголовками 'Accept' и 'Content-Type'.
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public class ApiXML extends AbstractApi {

    /**
     * Конструктор сервиса
     * 
     * @param url
     *            Url запроса
     */
    public ApiXML(String url) {

        super(url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareHeaders(HttpHeaders headers) {

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.setContentType(MediaType.APPLICATION_XML);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AbstractObject> T parse(String className, String input,
            T resultObj) {

        return parseXml(className, input, resultObj);
    }

    /**
     * Адаптация парсинга для XML формата
     * 
     * @param className
     *            Имя класса ридера, который будет обрабатывать данные
     * @param input
     *            Данные которые надо обработать
     * @param resultObj
     *            Объект в который нужно записать результат
     * @return Результат обработки
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractXmlParser<S>, S extends AbstractObject> S parseXml(
            String className, String input, S resultObj) {

        S result = null;
        SAXParserFactory f = SAXParserFactory.newInstance();
        InputStream stream = new ByteArrayInputStream(StringUtils.utf8(input));

        try {
            T parser = (T) Class.forName(className).newInstance();
            parser.setObject(resultObj);
            f.newSAXParser().parse(stream, parser);
            result = parser.getResult();
        } catch (Exception ex) {
            result = null;
            // throw new ApiException("Api exception", ex);
        }

        return result;
    }
}
