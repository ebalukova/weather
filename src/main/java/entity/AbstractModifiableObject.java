package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import utils.DateUtils;

/**
 * 
 * AbstractModifiableObject class
 * 
 * Объект, который может быть модифицирован. Содержит дополнительные поля о
 * времени последней модификации.
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 */
@MappedSuperclass
public abstract class AbstractModifiableObject extends AbstractObject {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -7613967382609625072L;

    /**
     * Время последней модификации объекта
     */
    private Date modificationTime;

    /**
     * Меняем дату модификации.
     */
    @PreUpdate
    void preSave() {

        setModificationTime(DateUtils.now());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "id", getUniqueKey()).toString();
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the modificationTime
     */
    @Column(name = "i_modification_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModificationTime() {

        return cloneDate(modificationTime);
    }

    /**
     * @param modificationTime
     *            the moditication time to be set
     */
    private void setModificationTime(Date modificationTime) {

        this.modificationTime = cloneDate(modificationTime);
    }
}
