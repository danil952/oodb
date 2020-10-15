package ru.icmit.oodb.lab5.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cashiers")
@XmlAccessorType (XmlAccessType.FIELD)
public class Cashiers
{
    @XmlElement(name = "cashier")
    private List<Cashier> cashiers = null;

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }
}