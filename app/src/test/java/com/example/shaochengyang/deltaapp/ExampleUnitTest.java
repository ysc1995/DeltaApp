package com.example.shaochengyang.deltaapp;

import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCustomerFlight(){
        CustomerFlight customerFlight
                = new CustomerFlight("sunnyday@gmail.com",
                "Day", "Sunny", "G13234345", "ticket123");
        String fname = customerFlight.getFname();
        String lname = customerFlight.getLname();
        assertEquals(fname,"Day");
        assertEquals(lname, "Sunny");
    }
}