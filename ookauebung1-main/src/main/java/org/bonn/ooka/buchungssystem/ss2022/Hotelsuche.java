package org.bonn.ooka.buchungssystem.ss2022;

import java.util.List;

public interface Hotelsuche {
    List<Hotel> getHotelByName(String name);
    List<String> getAvailableInterfaces(); // FA6: Returing available interfaces
}
