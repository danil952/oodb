package ru.icmit.oodb.lab1;

import java.util.List;

public class Shop {
    private String name;

    public static List<Cashier> cashiers;

    public static List<Goods> goods;

    public static List<Transaction> transactions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
