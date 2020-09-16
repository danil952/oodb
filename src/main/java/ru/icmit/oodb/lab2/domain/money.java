package ru.icmit.oodb.lab2.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class money {
    public static int getChange(int start,int quant)
    {
        if(start>quant) return start-quant;
        else
        {
            return -1;
        }
    }
    public static void Exchange(int sum)
    {
        List<Integer> list = new ArrayList<Integer>();
        list = Arrays.asList(new Integer[] {100,20,10,5,1});
        System.out.println("Размен: "+sum);
        for(int item: list)
        {
            System.out.println(item+": "+sum/item);
            sum=sum-item*(sum/item);
        }

    }
}
