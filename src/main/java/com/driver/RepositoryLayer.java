package com.driver;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class RepositoryLayer {

    HashMap<String, Hotel> hotelHashMap = new HashMap<>();
    HashMap<Integer, User> userHashMap = new HashMap<>();
    HashMap<String, Booking> bookingHashMap = new HashMap<>();

    public String addHotel(Hotel hotel){
        if(hotel == null || hotel.getHotelName() == null)return "FAILURE";
        else if(hotelHashMap.containsKey(hotel.getHotelName()))return "FAILURE";
        else{
            hotelHashMap.put(hotel.getHotelName(),hotel);
            return "SUCCESS";
        }
    }

    public int addUser(User user){
        userHashMap.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities(){
        int maxfacilities = 0;
        String maxFacilitiesHotelName = "";
        for(String hotelName : hotelHashMap.keySet()){
            if(hotelHashMap.get(hotelName).getFacilities().size() > maxfacilities){
                maxfacilities = hotelHashMap.get(hotelName).getFacilities().size();
                maxFacilitiesHotelName = hotelName;
            }
            else if(hotelHashMap.get(hotelName).getFacilities().size() == maxfacilities){
                if(hotelName.compareTo(maxFacilitiesHotelName) < 0){
                    maxFacilitiesHotelName = hotelName;
                }
            }
        }
        return maxFacilitiesHotelName;
    }

    public int bookARoom(Booking booking){
        UUID uuid = UUID.randomUUID();
        String bookingId = uuid.toString();
        int bookingAadharCard = booking.getBookingAadharCard();
        int noOfRooms = booking.getNoOfRooms();
        String bookingPersonName = booking.getBookingPersonName();
        String hotelName = booking.getHotelName();

        if(hotelHashMap.get(hotelName).getAvailableRooms() < booking.getNoOfRooms())return -1;
        int pricePerNight = hotelHashMap.get(hotelName).getPricePerNight();
        int amountToBePaid = pricePerNight * booking.getNoOfRooms();

        Hotel hotel = hotelHashMap.get(hotelName);
        hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());

        booking.setAmountToBePaid(amountToBePaid);
        booking.setBookingId(bookingId);

        bookingHashMap.put(bookingId,booking);

        return  amountToBePaid;
    }

    public int getBookings(int aadharCard){
        int numberOfBookings = 0;
        for (Booking booking : bookingHashMap.values()) {
            if(booking.getBookingAadharCard() == aadharCard)numberOfBookings++;
        }
        return numberOfBookings;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName){
        Hotel hotel = hotelHashMap.get(hotelName);
        List<Facility> oldFacilities = hotel.getFacilities();

        for(Facility facility : oldFacilities){
            if(!oldFacilities.contains(facility))oldFacilities.add(facility);
        }

        return hotel;
    }
}
