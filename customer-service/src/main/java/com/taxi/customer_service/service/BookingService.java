package com.taxi.customer_service.service;

import com.taxi.customer_service.entity.Booking;
import com.taxi.customer_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Long customerId,Long driverId){
        Booking booking=new Booking();
        booking.setCustomerId(customerId);
        booking.setDriverId(driverId);
        booking.setStatus("Pending");
        return bookingRepository.save(booking);

    }
}
