package entity;

/**
 * 
 * IdGenerator class
 * 
 * Генератор уникальных строковых значений
 * 
 */
public final class IdGenerator {

    /**
     * Скрываем конструктор для утилиты
     */
    private IdGenerator() {

        super();
    }

    /**
     * Генерация уникального идентификатора
     * 
     * @return Уникальный идентификатор в виде строки
     */
    public static String createId() {

        return java.util.UUID.randomUUID().toString();
    }
}
