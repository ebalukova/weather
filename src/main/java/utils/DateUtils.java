package utils;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * DateUtils class
 * 
 * Вспомогательные утилиты для работы с датами и временем.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */
public final class DateUtils {

    /**
     * Скрываем конструктор для утилиты
     */
    private DateUtils() {

        // do nothing
    }

    /**
     * Возвращает текущий момент времени, при этом, если выставлено текущее
     * время фиксированным, то DateTime использует именно его.
     * 
     * @return Текущий момент времени.
     */
    public static Date now() {

        return new DateTime().toDate();
    }

    /**
     * Клонирование даты. Помимо клонирования проверяем также на null.
     * 
     * @param date
     *            Дата
     * @return Копию даты
     */
    public static Date clone(Date date) {

        return ObjectUtils.isNull(date) ? null : (Date) date.clone();
    }

    /**
     * 
     * @param time
     *            заданный день
     * @return Начало заданного дня
     */
    public static Date beginningOfDay(Date time) {

        // у даты обнуляем время
        Date result = null;
        if (time != null) {
            DateTime dt = new DateTime(time.getTime());
            dt = dt.withTime(0, 0, 0, 0);
            result = dt.toDate();
        }

        return (result == null) ? null : result;
    }
}
