package ru.icmit.oodb.lab8;

import java.sql.Connection;
import java.sql.SQLException;

public interface EntityManager {

    /**
     * Метод сохраняет в БД объект var1
     */
    void persist(Object var1) throws Exception;

    /**
     * Метод обновляет в БД данные, соответствующие объекту
     */
    <T> T merge(T var1);

    /**
     * Метод удаляет объект из БД
     */
    void remove(Object var1);

    /**
     * Метод запрашивает из базы данных информацию, соответствующую первичному ключу var2.
     * Полученные данные инициализируют объект типа Class<T>
     * Метод возвращает созданный объект
     * @param var1 - тип создаваемого объекта
     * @param var2 - значение первичного ключа
     */
    <T> T find(Class<T> var1, Object var2) throws SQLException;

    void refresh(Object var1);
}
