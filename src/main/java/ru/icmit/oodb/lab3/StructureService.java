package ru.icmit.oodb.lab3;

import ru.icmit.oodb.lab2.domain.Cashier;
import ru.icmit.oodb.lab2.domain.Goods;
import ru.icmit.oodb.lab2.domain.GoodsType;
import ru.icmit.oodb.lab2.domain.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Класс сервисных методов для работы с данными
 */
public class StructureService {

    /**
     * Поиск по имени
     */
    public static Cashier findCashierByName(List<Cashier> cashiers, String searchName) {
        Cashier result = null;

        for (Cashier cashier : cashiers) {
            if (cashier.getName().equals(searchName)) {
                result = cashier;
            }
        }

        return result;
    }

    public static List<Goods> findGoodsByType(List<Goods> goods, GoodsType type) {
        List<Goods> result = null;

        for (Goods good : goods) {
            if (good.getType().equals(type)) {
                result.add(good);
            }
        }

        return result;
    }

    public static List<Transaction> findTransactionsByCashierName(List<Transaction> transactions, String name) {
        List<Transaction> result = null;

        for (Transaction transaction : transactions) {
            if (transaction.getCashier().getName().equals(name)) {
                result.add(transaction);
            }
        }

        return result;
    }

    public static List<Goods> sortGoodsByPrice(List<Goods> list)
    {

        List<Goods> result = list
                .stream()
                .sorted(Comparator.comparing(Goods::getCost))
                .collect(Collectors.toList());
        return result;



    }

}
