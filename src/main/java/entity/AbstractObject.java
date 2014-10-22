package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utils.DateUtils;

/**
 * 
 * AbstractObject class
 * 
 * Базовый объект для модельных объектов
 * 
 */
@MappedSuperclass
public abstract class AbstractObject extends AbstractEntityObject {

    /**
     * 
     */
    private static final long serialVersionUID = -8577409951409168733L;

    /**
     * Database-provided идентификатор.
     */
    private Long id;

    /**
     * Время создания объекта. Устанавливается непосредственно перед моментом
     * сохранения (только для EntityManager это делается автоматически, в
     * остальных случаях только при создании Java-объекта)
     */
    private Date creationTime;

    /**
     * Универсальное поле сортировки.
     */
    private Date sortDate;

    /**
     * Конструктор без параметров
     */
    public AbstractObject() {

        super();
    }

    /**
     * Такой конструктор может понадобиться, когда требуется создание объекта с
     * заданным уникальным идентификатором
     * 
     * @param uniqueKey
     *            Уникальный идентификатор, присваиваемый извне
     */
    public AbstractObject(String uniqueKey) {

        super(uniqueKey);
    }

    /**
     * Создаем дату
     */
    @PrePersist
    public void preCreate() {

        Date now = DateUtils.now();
        setCreationTime(now);
        if (sortDate == null) {
            setSortDate(now);
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return Идентификатор - первичный ключ в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {

        return id;
    }

    /**
     * @param id
     *            Идентификатор - первичный ключ в БД
     */
    @Override
    protected void setId(Long id) {

        this.id = id;
    }

    /**
     * @return Время создания записи
     */
    @Column(name = "i_creation_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationTime() {

        return cloneDate(creationTime);
    }

    /**
     * @return дата для сортировки
     */
    @Column(name = "i_sort_date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getSortDate() {

        return cloneDate(sortDate);
    }

    /**
     * @param creationTime
     *            Время создания записи
     */
    private void setCreationTime(Date creationTime) {

        this.creationTime = cloneDate(creationTime);
    }

    /**
     * @param sortDate
     *            Дата сортировки
     */
    public void setSortDate(Date sortDate) {

        this.sortDate = DateUtils.beginningOfDay(sortDate);
    }
}
