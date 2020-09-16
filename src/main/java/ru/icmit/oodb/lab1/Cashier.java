package ru.icmit.oodb.lab1;



import java.util.List;

public class Cashier {
    private String name;
    private Shop shop;

    public Cashier(String name,Shop shop)
    {
        this.name=name;
        this.shop=shop;
    }

    public static void toSell(List<Goods> goodslist,int visitorMoney)
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
            System.out.println("Спасибо за покупку!Сумма к оплате: "+sum+"\n"+"Ваша сдача: "+money.getChange(visitorMoney, sum));
        }

    }

    public Shop getShop() {
        return shop;
    }

    public String getName() {
        return name;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setName(String name) {
        this.name = name;
    }
}
