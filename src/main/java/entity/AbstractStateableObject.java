/**
 *
 */
package entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * AbstractStateableObject class
 * 
 * Объект, который может иметь состояния. Содержит дополнительные поле
 * отражающее его состояние
 * 
 * 
 * @author Balukova Elena
 * @created 18 окт. 2014 г.
 * 
 */
@MappedSuperclass
public class AbstractStateableObject<T> extends AbstractModifiableObject {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 177204291008026845L;

    /*
     * Не разобралась с apache derby как вставить VARCHAR for BIT DATA, поэтому
     * убрала параметризацию
     */
    // private T state;
    /**
     * Состояние объекта
     */
    private String state;

    // ////////////////////////////////////////////////////////////////////////
    // //// getters/setters
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @return the state
     */
    @Column(name = "state")
    // @Enumerated(EnumType.STRING)
    public String getState() {

        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {

        this.state = state;
    }
}
