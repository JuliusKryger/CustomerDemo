package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author 23300
 */
@Entity
public class Customer implements Serializable {

    private String firstName;
    private String lastName;
    //@Temporal(TemporalType.DATE)
    LocalDateTime created;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        created = LocalDateTime.now();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", created=" + created +
                ", id=" + id +
                '}';
    }
}