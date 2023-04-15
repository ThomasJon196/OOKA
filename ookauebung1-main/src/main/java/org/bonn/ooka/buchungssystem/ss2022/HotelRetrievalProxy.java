package org.bonn.ooka.buchungssystem.ss2022;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelRetrievalProxy implements Hotelsuche {

    private HotelRetrieval hotelRetrieval;
    private Logger logger;

    public HotelRetrievalProxy(HotelRetrieval hotelRetrieval) {
        this.hotelRetrieval = hotelRetrieval;
        logger = Logger.getLogger(HotelRetrieval.class.getName());
    }

    public List<Hotel> getHotelByName(String name) {
        logger.log(Level.INFO, "Zugriff auf Buchungssystem über Methode getHotelByName. Suchwort: {0}", name);
        return hotelRetrieval.getHotelByName(name);
    }

    public List<String> getAvailableInterfaces() {
        List<String> availableInterfaces = new ArrayList<>();
        availableInterfaces.add("1. List<Hotel> getHotelByName(String name)");  
        return availableInterfaces;
    }

    public static void main(String[] args) {
        System.out.println("Executing main from Class HotelRetrievalProxy");
        HotelRetrieval hotelRet = new HotelRetrieval();
        HotelRetrievalProxy hotelRet_proxy = new HotelRetrievalProxy(hotelRet);

        List<Hotel> result = hotelRet_proxy.getHotelByName("*");
        for (Hotel hotel : result) {
            System.out.println(hotel.toString());
        }

        System.out.println("Setting Cache");
        hotelRet.set_cache(new HotelCaching());

        result = hotelRet.getHotelByName("*");
        for (Hotel hotel : result) {
            System.out.println(hotel.toString());
        }

        result = hotelRet.getHotelByName("*");
        for (Hotel hotel : result) {
            System.out.println(hotel.toString());
        }

    }

}