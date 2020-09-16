package ru.icmit.oodb.lab2;

import com.google.gson.Gson;
import ru.icmit.oodb.lab2.domain.Cashier;
import ru.icmit.oodb.lab2.domain.Shop;

import java.io.*;
import java.util.List;

public class Lab2SaveDB {

    public static void saveStructureList(List structure,String file) throws IOException {
        if (structure != null && structure.size() > 0) {
            Gson gson = new Gson();

            String structureAsJson = gson.toJson(structure);

            System.out.println(structureAsJson);

            try (OutputStream os = new FileOutputStream(new File(file))) {
                os.write(structureAsJson.getBytes("UTF-8"));
                os.flush();
            }

        }
    }

    public static void saveShop(Shop shop) throws IOException
    {
        Gson gson = new Gson();

        String shopAsJson = gson.toJson(shop);

        System.out.println(shopAsJson);

        try (OutputStream os = new FileOutputStream(new File("shop.json"))) {
            os.write(shopAsJson.getBytes("UTF-8"));
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
