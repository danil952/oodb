package ru.icmit.oodb.lab3;

import ru.icmit.oodb.lab3.domain.Shop;
import ru.icmit.oodb.lab3.domain.Cashiers;
import ru.icmit.oodb.lab3.domain.Transactions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class Lab3XMLService {
    public static void saveCashiersList(Cashiers structure, String file) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Cashiers.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(structure, System.out);
        jaxbMarshaller.marshal(structure, new File(file));
    }

    public static void saveTransactionsList(Transactions structure, String file) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Transactions.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(structure, System.out);
        jaxbMarshaller.marshal(structure, new File(file));
    }

    public static void saveShopData(Shop shop) {

        try {
            JAXBContext context = JAXBContext.newInstance(Shop.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(shop, new File("shop.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Shop loadShopFromXML() {

        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Shop) un.unmarshal(new File("shop.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Transactions loadTransactionsFromXML() {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Transactions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Transactions transactions = (Transactions) jaxbUnmarshaller.unmarshal( new File("transactions.xml") );
            return  transactions;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }


}
