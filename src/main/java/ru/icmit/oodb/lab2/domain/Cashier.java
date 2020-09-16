package ru.icmit.oodb.lab2.domain;



import java.util.List;

public class Cashier {

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

