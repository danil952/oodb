package ru.icmit.oodb.lab8;

import ru.icmit.oodb.lab8.domain.Cashier;

import java.util.Properties;

public class Lab8 {

    public static void main(String[] args) throws Exception {
        Properties dbProperties = new Properties();
        dbProperties.setProperty("url","jdbc:postgresql://localhost:1345/lab7");
        dbProperties.setProperty("username","postgres");
        dbProperties.setProperty("password","3005952");
        EntityManagerFactory factory = new EntityManagerFactory(dbProperties);
        EntityManagerImpl e = factory.createEntityManager();
        Cashier cashier = new Cashier();
        cashier.setId(11);
        cashier.setLastname("Ivanov");
        cashier.setName("Ivan");
        cashier.setShift("Night");
        e.persist(cashier);

        Cashier obj = e.find(Cashier.class,11 );
        System.out.println(obj.getName());
        obj.setName("Danil");
        e.merge(obj);
        obj=e.find(Cashier.class,11 );
        System.out.println(obj.getName());
    }
}
