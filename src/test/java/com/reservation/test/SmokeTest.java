package com.reservation.test;

import com.reservation.test.controllers.PersonController;
import com.reservation.test.controllers.ReservationController;
import com.reservation.test.controllers.RoomController;
import com.reservation.test.entities.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private PersonController personController;

    @Autowired
    private RoomController roomController;

    @Test
    public void contextLoads() throws Exception {

        Assertions.assertNotNull(personController);
        Assertions.assertNotNull(roomController);
        Assertions.assertNotNull(reservationController);
    }
}
