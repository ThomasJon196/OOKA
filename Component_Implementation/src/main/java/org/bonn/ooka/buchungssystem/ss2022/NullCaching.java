package org.bonn.ooka.buchungssystem.ss2022;

import java.util.List;

public class NullCaching implements Caching {

    public List<Hotel> retrieve(String keyword) {
        return null;
    }

    public void store(String keyword, List<Hotel> hotels) {
    }

}
