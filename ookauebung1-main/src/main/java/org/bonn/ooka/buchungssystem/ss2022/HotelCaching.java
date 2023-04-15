package org.bonn.ooka.buchungssystem.ss2022;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelCaching implements Caching {
    private Map<String, List<Hotel>> cache;

    public HotelCaching() {
        cache = new HashMap<>();
    }

    public List<Hotel> retrieve(String keyword) {
        try {
            return cache.get(keyword);
        } catch (Exception e) {
            return null;
        }
    }

    public void store(String keyword, List<Hotel> hotels) {
        cache.put(keyword, hotels);
    }

}
