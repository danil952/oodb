package ru.icmit.oodb.lab3;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.icmit.oodb.lab2.domain.Cashier;
import ru.icmit.oodb.lab2.domain.Goods;
import ru.icmit.oodb.lab2.domain.Transaction;
import ru.icmit.oodb.lab3.domain.Shop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Lab3LoadDB {
    public static void main(String[] args) {

        Shop newShop = Lab3XMLService.loadShopFromXML();
        System.out.println(newShop.getName());

    }


}
