/**
 * 
 */
package api;

import org.springframework.core.NestedRuntimeException;

/**
 * Внутреннее nested - исключение.
 * 
 * 
 * @author Balukova Elena
 * @created 19 окт. 2014 г.
 * 
 */

public class ApiException extends NestedRuntimeException {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 6986808307173357420L;

    /**
     * Конструктор по умолчанию
     * 
     * @param msg
     *            Текст сообщения об ошибке
     * @param cause
     *            Исходное исключение
     */
    public ApiException(String msg, Throwable cause) {

        super(msg, cause);
    }
}
