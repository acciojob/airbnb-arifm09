package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    Repository repository;
    public void addHotel(Hotel hotel) {
        repository.addHotel(hotel);
    }

    public void addUser(User user) {
        repository.addUser(user);
    }

    public String getHotelWithMostFacilities() {
       return repository.getHotelWithMostFacilities();
    }

    public Integer bookARoom(Booking booking) {
       return repository.bookARoom(booking);
    }

    public Booking getBookings(int aadharCard) {
        return repository.getBookings(aadharCard);
    }

    public Hotel updateFacalities(List<Facility> newFacilities, String hotelName) {

       return repository.updateFacalities(newFacilities,hotelName);
    }
}
