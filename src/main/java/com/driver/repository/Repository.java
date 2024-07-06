package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.util.ObjectUtils;

import java.util.*;

@org.springframework.stereotype.Repository
public class Repository {

    Map<String,Hotel> hotelDb =  new HashMap<>();
    Map<Integer, User> userDb = new HashMap<>();
    Map<String,Booking> bookingDb = new HashMap<>();
    public void addHotel(Hotel hotel) {
        if(ObjectUtils.isEmpty(hotel)){
            throw new RuntimeException();
        }
        if(hotel.getHotelName().isEmpty()){
            throw new RuntimeException();
        }

        hotelDb.put(hotel.getHotelName(),hotel);

    }

    public void addUser(User user) {
        userDb.put(user.getaadharCardNo(),user);
    }

    public String getHotelWithMostFacilities() {
        int mxCnt = 0;
        String hotelName = "";
        for(String hotel : hotelDb.keySet()){

            if(hotelDb.get(hotel).getFacilities().size()>=mxCnt){
                    hotelName = hotel;
                    mxCnt = hotelDb.get(hotel).getFacilities().size();
                }
            else if(hotelDb.get(hotel).getFacilities().size()==mxCnt && hotel.compareTo(hotelName)<0){
               hotelName = hotel;
            }
        }
        return hotelName;
    }

    public int bookARoom(Booking booking) {

        String bookingId = String.valueOf(UUID.randomUUID());
        int bookingAadharCard = booking.getBookingAadharCard();
        int noOfRooms = booking.getNoOfRooms();
        String personName = booking.getBookingPersonName();
        String hotelName = booking.getHotelName();
        int amountToBePaid = -1;

        Booking newBook = new Booking(bookingAadharCard,noOfRooms,personName,hotelName);

        for(String hotel: hotelDb.keySet()){
           int canBook = hotelDb.get(hotel).getAvailableRooms()-noOfRooms;

           if(hotelName.equals(hotel) && canBook>=0){
               hotelName = hotel;
               amountToBePaid = hotelDb.get(hotel).getPricePerNight()*noOfRooms;
               booking.setAmountToBePaid(amountToBePaid);
               booking.setBookingId(bookingId);
               bookingDb.put(bookingId,booking);

               break;
           }
        }

        return amountToBePaid;

    }

    public Booking getBookings(int aadharCard) {

        for(String booking: bookingDb.keySet()){
            if(bookingDb.get(booking).getBookingAadharCard()==aadharCard){
                return bookingDb.get(booking);
            }
        }

        return null;
    }

    public Hotel updateFacalities(List<Facility> newFacilities, String hotelName) {

        for(String hotel : hotelDb.keySet()){

            if(hotel.equals(hotelName)){

                List<Facility> tempFacilities = hotelDb.get(hotel).getFacilities();
                for(Facility ele : newFacilities){

                    if(!(tempFacilities.contains(ele))){
                        tempFacilities.add(ele);

                    }
                }
                hotelDb.get(hotel).setFacilities(tempFacilities);
                return hotelDb.get(hotel);
            }
        }
        return  null;
    }
}

