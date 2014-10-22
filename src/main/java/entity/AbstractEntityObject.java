package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import utils.DateUtils;
import utils.ObjectUtils;

/**
 * 
 * AbstractEntityObject class
 * 
 * Базовый объект с идентификатором.
 * 
 * Содержит общий механизм присвоения hash-id при создании объекта и алгоритм
 * сравнения с учётом этого ключа и id из БД
 * 
 * Ключ в БД (ID) объявлен абстрактным, чтобы в потомках учитывать специфику
 * генерации этого ключа (SEQUENCE, IDENTITY и т.д.)
 * 
 */
@MappedSuperclass
public abstract class AbstractEntityObject implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -8577409951409168733L;

    /**
     * Application-based уникальный идентификатор.
     */
    private String internalId = IdGenerator.createId();

    /**
     * Конструктор по умолчанию требует Hibernate
     */
    public AbstractEntityObject() {

        super();
    }

    /**
     * Является ли данный объект новым. Критерием служит отсутствие
     * идентификатора.
     * 
     * @return true, если объект ещё не был сохранен в БД, иначе false
     */
    @Transient
    public boolean isNew() {

        return ObjectUtils.isNull(getId());
    }

    /**
     * Конструктор с заданным уникальным идентификатором
     * 
     * @param uniqueKey
     *            Уникальный идентификатор, присваиваемый извне
     */
    public AbstractEntityObject(String uniqueKey) {

        this();
        setInternalId(uniqueKey);
    }

    /**
     * Базовый вариант уникального идентификатора для объекта. Программный
     * идентификатор (internalId) используется только в случае, если объект не
     * сохранен в базу.
     * 
     * @return Унильное значение для объекта
     */
    @Transient
    protected String getUniqueKey() {

        Long identifier = getId();
        return ObjectUtils.isNull(identifier) ? this.getInternalId()
                : identifier.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "id", getUniqueKey()).toString();
    }

    /**
     * Сравнение по умолчанию работает по уникальному идентификатору.
     */
    @Override
    @SuppressWarnings("PMD.ConfusingTernary")
    public boolean equals(Object o) {

        boolean result;
        if (this == o) {
            result = true;
        } else if (!(o instanceof AbstractEntityObject)) {
            result = false;
        } else if (!(this.getClass().isInstance(o))) {
            result = false;
        } else {

            final AbstractEntityObject object = (AbstractEntityObject) o;
            String uniqueId = getUniqueKey();

            result = uniqueId.equals(object.getUniqueKey());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        String unique = getUniqueKey();
        return unique.hashCode();
    }

    /**
     * С java.util.Date всегда нужно работать через клонирование, поскольку она
     * передается по ссылке и возможно непреднамеренно испортить поле
     * 
     * @param d
     *            Дата, которую требуется клонировать
     * @return Клонированная дата
     */
    protected Date cloneDate(Date d) {

        return DateUtils.clone(d);
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return Идентификатор - первичный ключ в БД
     */
    @Column(name = "id", insertable = false, updatable = false)
    public abstract Long getId();

    /**
     * @param id
     *            Идентификатор - первичный ключ в БД
     */
    protected abstract void setId(Long id);

    /**
     * @return the internalId
     */
    @Transient
    public String getInternalId() {

        return internalId;
    }

    /**
     * @param internalId
     *            the internalId to set
     */
    private void setInternalId(String internalId) {

        this.internalId = internalId;
    }
}
