package utils;

import java.io.UnsupportedEncodingException;

/**
 * StringUtils class
 * 
 * Утилиты для работы со строками.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */
public final class StringUtils {

    /**
     * Скрываем конструктор для утилиты
     */
    private StringUtils() {

        // do nothing
    }

    /**
     * Преобразоварние байт в строку в кодировке utf-8 с подавлением исключения
     * 
     * @param bytes
     *            Байты строки
     * @return Строка в кодировке utf-8
     */
    public static String utf8(byte[] bytes) {

        try {
            return new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Преобразоварние строки в байты кодировки utf-8
     * 
     * @param s
     *            Строка
     * @return Байты строки в кодировке utf-8
     */
    public static byte[] utf8(String s) {

        try {
            return s.getBytes("utf-8");
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
