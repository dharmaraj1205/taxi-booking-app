package com.taxi.customer_service.controller;

import com.taxi.customer_service.entity.Booking;
import com.taxi.customer_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("createBooking")
    public ResponseEntity<Booking> createBooking(@RequestParam Long customerId, @RequestParam Long driverId){
        Booking booking=bookingService.createBooking(customerId, driverId);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);

    }
}
