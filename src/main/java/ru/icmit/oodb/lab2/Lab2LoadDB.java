package ru.icmit.oodb.lab2;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.icmit.oodb.lab2.domain.Cashier;
import ru.icmit.oodb.lab2.domain.Goods;
import ru.icmit.oodb.lab2.domain.Shop;
import ru.icmit.oodb.lab2.domain.Transaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Lab2LoadDB {

    public static Shop load() throws IOException {
        Shop shop = null;
        String shopStr = "";
        File file = new File("shop.json");

        if (file.exists()) {
            shopStr = new String(Files.readAllBytes(file.toPath()));
        }

        shop = new Gson().fromJson(shopStr, Shop.class);

        return shop;
    }

    /**
     * Пример чтения из файла массива JSON объектов
     */
    public static List loadCashierList() throws IOException, JsonSyntaxException {
        String pStr = "";
        File file = new File("cashier.json");

        if (file.exists()) {
            pStr = new String(Files.readAllBytes(file.toPath()));
        } else {
            System.out.println("File cashier.json not found!");
        }

        Gson gson = new Gson();

        Cashier[] plst = gson.fromJson(pStr, Cashier[].class);

        return Arrays.asList(plst);
    }

    public static List loadGoodsList() throws IOException, JsonSyntaxException {
        String pStr = "";
        File file = new File("goods.json");

        if (file.exists()) {
            pStr = new String(Files.readAllBytes(file.toPath()));
        } else {
            System.out.println("File goods.json not found!");
        }

        Gson gson = new Gson();

        Goods[] plst = gson.fromJson(pStr, Goods[].class);

        return Arrays.asList(plst);
    }

    public static List loadTransactionList() throws IOException, JsonSyntaxException {
        String pStr = "";
        File file = new File("transaction.json");

        if (file.exists()) {
            pStr = new String(Files.readAllBytes(file.toPath()));
        } else {
            System.out.println("File transaction.json not found!");
        }

        Gson gson = new Gson();

        Transaction[] plst = gson.fromJson(pStr, Transaction[].class);

        return Arrays.asList(plst);
    }
}
