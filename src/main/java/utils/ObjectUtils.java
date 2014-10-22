package utils;

import static org.springframework.util.ObjectUtils.nullSafeEquals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ClassUtils;

/**
 * ObjectUtils class
 * 
 * Некоторые общие утилиты.
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */
public final class ObjectUtils {

    /**
     * Скрываем конструктор для утилиты
     * 
     * @param empty
     *            Пустой параметр
     */
    private ObjectUtils(String empty) {

        super();
        assert empty != null; // to get 100 % test coverage in getInstance()
    }

    /**
     * Подобная проверка в коде выглядит более выразительно и внятно, чем
     * использование средств языка Java.
     * 
     * @param object
     *            Анализируемый объект
     * @return true, если объект является null'ом
     */
    public static boolean isNull(Object object) {

        return object == null;
    }

    /**
     * Функция обратная к isNull
     * 
     * @param object
     *            Анализируемый объект
     * @return true, если объект не является null'ом
     */
    public static boolean notNull(Object object) {

        return not(isNull(object));
    }

    /**
     * Выделение ложного выражения. Стандартная языковая конструкция с
     * использованием <code>'!'</code> не всегда внятно различима.
     * 
     * @param expression
     *            Логическое выражение/переменная
     * @return true, если условие ложно
     */
    public static boolean not(boolean expression) {

        return !(expression);
    }

    /**
     * Проверка того, что два объекта не равны друг другу с защитой от NPE.
     * 
     * @param value1
     *            Значение № 1
     * @param value2
     *            Значение № 2
     * @return true, если объекты не равны.
     */
    public static boolean notEquals(Object value1, Object value2) {

        return not(nullSafeEquals(value1, value2));
    }

    /**
     * Создание экземпляра класса с перехватом всех исключений. Почему-то не
     * нашел нигде такой простой и полезной утилиты, хотя думал, что в Spring
     * всё есть.
     * 
     * @param <S>
     *            Тип объекта
     * @param clazz
     *            Класс объекта
     * @return Созданный экземпляр
     */
    public static <S> S getInstance(Class<S> clazz) {

        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Создание экземпляра класса по его строковому имени с перехватом всех
     * исключений.
     * 
     * @param <S>
     *            Тип объекта
     * @param className
     *            Имя класса
     * @return Созданный экземпляр
     */
    @SuppressWarnings("unchecked")
    public static <S> S getInstance(String className) {

        try {
            return (S) getInstance(ClassUtils.forName(className,
                    ClassUtils.getDefaultClassLoader()));
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Полчение класса по имени
     * 
     * @param className
     *            Имя класса
     * 
     * @param <S>
     *            Тип объекта
     * @return class
     */
    @SuppressWarnings("unchecked")
    public static <S> Class<S> classByName(String className) {

        try {
            return (Class<S>) ClassUtils.forName(className,
                    ClassUtils.getDefaultClassLoader());
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Обертка для null-безопасной работы со списками
     * 
     * @param <T>
     *            тип элемента списка
     * @param r
     *            список, возможно null
     * 
     * @return список, гарантировано не null
     */
    public static <T extends Object> List<T> nullSafeList(List<T> r) {

        return isNull(r) ? new ArrayList<T>() : r;
    }
}
