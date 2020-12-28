package ru.icmit.oodb.lab7.domain;



import ru.icmit.oodb.lab7.annotation.Column;
import ru.icmit.oodb.lab7.annotation.Entity;
import ru.icmit.oodb.lab7.annotation.Id;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
public class Cashier {

    public Cashier(){}


    public enum Shift {
        NIGHT,
        DAY
    }

    @Column
    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private Shift shift;

    public Cashier(long id,String name, String lastname,Shift shift)
    {
        this.id=id;
        this.name=name;
        this.lastname =lastname;
        this.shift=shift;
    }


    public String getName() {
        return name;
    }

    public Shift getShift() {
        return shift;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

