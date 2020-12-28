package ru.icmit.oodb.lab6.domain;



import ru.icmit.oodb.lab6.annotation.Column;
import ru.icmit.oodb.lab6.annotation.Entity;

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
    private String name;

    @Column
    private String lastname;

    @Column
    private Shift shift;

    public Cashier(String name, String lastname,Shift shift)
    {
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
}

