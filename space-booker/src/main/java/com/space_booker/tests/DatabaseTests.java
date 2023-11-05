package com.space_booker.tests;

import com.space_booker.model.Booker;
import com.space_booker.model.Booking;
import com.space_booker.model.SerializationModule;
import com.space_booker.model.User;

import java.util.Arrays;

public class DatabaseTests {
    /*
    Test Cases for the SerializationModule methods for
    reading/writing in the database

    Full test descriptions on project gitlab
    https://git.cs.usask.ca/phoenix-squad/phoenix/-/wikis/Deliverable_6
     */
    public static void main(String[] args) {
        User testUser = new Booker("Ben", "bes936@usask.ca", "******");
        //TC01 : Empty list
        Booking[] bookings1 = {};
        try {
            SerializationModule.SaveBookings(bookings1, "./testData.dat");
        } catch (RuntimeException e) {
            System.out.println("TC01 FAILURE : SaveBookings threw an exception.");
        }

        Booking[] bookings2;
        try {
            bookings2 = SerializationModule.LoadBookings("./testData.dat");
        } catch (RuntimeException e) {
            System.out.println("TC01 FAILURE : LoadBookings threw an exception.");
            bookings2 = new Booking[]{null};
        }

        if (!Arrays.equals(bookings1, bookings2)) {
            System.out.println("TC01 FAILURE : returned an unexpected value.");
            System.out.println(Arrays.toString(bookings1));
            System.out.println(Arrays.toString(bookings2));
        }

        //TC02 : List with one element
        Booking[] bookings3 = {new Booking(0, testUser, "1", "2", Booking.BookingStatus.PENDING)};
        try {
            SerializationModule.SaveBookings(bookings3, "./testData.dat");
        } catch (RuntimeException e) {
            System.out.println("TC02 FAILURE : SaveBookings threw an exception.");
        }

        Booking[] bookings4;
        try {
            bookings4 = SerializationModule.LoadBookings("./testData.dat");
        } catch (RuntimeException e) {
            System.out.println("TC02 FAILURE : LoadBookings threw an exception.");
            bookings4 = new Booking[]{null};
        }

        if (!Arrays.equals(bookings3, bookings4)) {
            System.out.println("TC02 data not identical. Verify correctness");
            System.out.println(Arrays.toString(bookings3));
            System.out.println(Arrays.toString(bookings4));
        }

        //TC03 : List with many elements
        Booking[] bookings5 = {new Booking(0, testUser, "1", "2", Booking.BookingStatus.PENDING),
                new Booking(1, testUser, "3", "4", Booking.BookingStatus.APPROVED),
                new Booking(4, testUser, "2", "1", Booking.BookingStatus.DECLINE)};
        try {
            SerializationModule.SaveBookings(bookings5, "./testData.dat");
        } catch (RuntimeException e) {
            System.out.println("TC03 FAILURE : SaveBookings threw an exception.");
        }

        Booking[] bookings6;
        try {
            bookings6 = SerializationModule.LoadBookings("./testData.dat");
        } catch (RuntimeException e) {
            System.out.println("TC03 FAILURE : LoadBookings threw an exception.");
            bookings6 = new Booking[]{null};
        }

        if (!Arrays.equals(bookings5, bookings6)) {
            System.out.println("TC03 data not identical. Verify correctness");
            System.out.println(Arrays.toString(bookings5));
            System.out.println(Arrays.toString(bookings6));
        }

        System.out.println("Test cases complete");
    }

}
