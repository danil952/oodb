package ru.icmit.oodb.lab8.domain;



import ru.icmit.oodb.lab8.annotation.Column;
import ru.icmit.oodb.lab8.annotation.Entity;
import ru.icmit.oodb.lab8.annotation.Id;

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
    private int id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private String shift;

    public Cashier(int id,String name, String lastname,String shift)
    {
        this.id=id;
        this.name=name;
        this.lastname =lastname;
        this.shift=shift;
    }


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

