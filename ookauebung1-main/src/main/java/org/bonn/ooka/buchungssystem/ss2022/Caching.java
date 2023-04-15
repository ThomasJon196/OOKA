package org.bonn.ooka.buchungssystem.ss2022;
import java.util.List;


public interface Caching {
    List<Hotel> retrieve(String keyword);
    void store(String keyword, List<Hotel> hotels);
}
