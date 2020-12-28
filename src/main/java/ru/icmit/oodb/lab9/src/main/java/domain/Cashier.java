package domain;



import javax.persistence.*;


@Entity
public class Cashier {

    @Id
    private int id;

    private String name;

    private String lastname;

    private String shift;


    public String getName() {
        return name;
    }

    public String getShift() {
        return shift;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
}

