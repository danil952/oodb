package ru.icmit.oodb.lab5.domain;



import org.postgresql.util.PGobject;
import org.postgresql.util.PGtokenizer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.SQLException;
import java.util.List;
@XmlRootElement(name = "cashier")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cashier extends PGobject {

    public Cashier(){
        setType("cashier");
    }


    public enum Shift {
        NIGHT,
        DAY
    }
    private String name;
    private String lastname;
    private Shift shift;

    public Cashier(String name, String lastname,Shift shift)
    {
        this.name=name;
        this.lastname =lastname;
        this.shift=shift;
    }

    public void setValue(String value) throws SQLException {
        String s = value.substring(1, value.length()-1);
        PGtokenizer t = new PGtokenizer(s, ',');
        name = t.getToken(0).substring(1, t.getToken(0).length()-1);
        lastname = t.getToken(1).substring(1, t.getToken(1).length()-1);
        shift = Shift.valueOf(t.getToken(2).substring(1, t.getToken(2).length()-1));
    }

    public String getValue() {
        return "('" + name + "','" + lastname + "','" +shift.toString()+ "')";
    }

    public static void toSell(List<Goods> goodslist, int visitorMoney)
    {
        int sum=0;
        System.out.println("Список покупок:");
        for(Goods item: goodslist)
        {
            if(item.getQuantity()>=1)
            {
                sum=sum+item.getCost();
                System.out.println(item.toString());
            }

        }
        if(money.getChange(visitorMoney, sum)==-1)
        {
            System.out.println("Недостаточно денег");
        }
        else
        {
            for(Goods item: goodslist)item.setQuantity(item.getQuantity()-1);
            System.out.println("Спасибо за покупку!Сумма к оплате: "+sum+"\n"+"Ваша сдача: "+ money.getChange(visitorMoney, sum));
        }

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

