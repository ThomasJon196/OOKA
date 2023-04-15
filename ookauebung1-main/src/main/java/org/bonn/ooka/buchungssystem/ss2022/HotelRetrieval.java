package org.bonn.ooka.buchungssystem.ss2022;

import java.util.List;

public class HotelRetrieval implements Hotelsuche {

    private DBAccess dbAccess;
    private Caching cache;
    // private Logger logger;

    public HotelRetrieval() {
        this.dbAccess = new DBAccess();
        this.cache = new NullCaching(); // FA3: Setting default NullCache

    }

    // FA3: Settings cache dynamicaly
    public void set_cache(Caching cache) {
        this.cache = cache;
    }

    public List<Hotel> getHotelByName(String name) {

        List<Hotel> result = cache.retrieve(name);

        if (result != null) {
            System.out.println("REMOVE! Retrieved from cache.");
            return result;
        }

        // Open connection before accessing the database
        dbAccess.openConnection();

        // Get hotel information from DBAccess class
        result = dbAccess.getObjects(name);

        // Close connection after accessing the database
        dbAccess.closeConnection();

        cache.store(name, result);
        System.out.println("REMOVE! Saved to cache.");

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Executing main from Class HotelRetrieval");

        HotelRetrieval hotelRet = new HotelRetrieval();

        List<Hotel> result = hotelRet.getHotelByName("*");
        for (Hotel hotel : result) {
            System.out.println(hotel.toString());
        }


        result = hotelRet.getHotelByName("*");
        for (Hotel hotel : result) {
            System.out.println(hotel.toString());
        }


    }

    @Override
    public List<String> getAvailableInterfaces() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableInterfaces'");
    }

}
