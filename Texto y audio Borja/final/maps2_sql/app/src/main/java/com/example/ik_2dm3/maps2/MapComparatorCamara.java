package com.example.ik_2dm3.maps2;

/**
 * Created by SHAJIB on 7/16/2017.
 */

import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by SHAJIB on 7/12/2017.
 */

class MapComparatorCamara implements Comparator<HashMap<String, String>>
{
    private final String key;
    private final String order;

    public MapComparatorCamara(String key, String order)
    {
        this.key = key;
        this.order = order;
    }

    public int compare(HashMap<String, String> first,
                       HashMap<String, String> second)
    {
        // TODO: Null checking, both for maps and values
        String firstValue = first.get(key);
        String secondValue = second.get(key);
        if(this.order.toLowerCase().contentEquals("asc"))
        {
            return firstValue.compareTo(secondValue);
        }else{
            return secondValue.compareTo(firstValue);
        }

    }
}