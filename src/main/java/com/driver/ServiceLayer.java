package com.driver;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ServiceLayer {

//    @Autowired
//    private RepositoryLayer repositoryLayer;
    RepositoryLayer repositoryLayer = new RepositoryLayer();

    public String addHotel(Hotel hotel){
        return repositoryLayer.addHotel(hotel);
    }

    public int addUser(User user){
        return repositoryLayer.addUser(user);
    }

    public String getHotelWithMostFacilities(){
        return repositoryLayer.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking){
        return repositoryLayer.bookARoom(booking);
    }

    public int getBookings(int aadharCard){
        return repositoryLayer.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName){
        return repositoryLayer.updateFacilities(newFacilities,hotelName);
    }
}
