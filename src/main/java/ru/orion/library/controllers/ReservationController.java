package ru.orion.library.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.orion.library.models.Account;
import ru.orion.library.models.Reservation;
import ru.orion.library.services.AccountService;
import ru.orion.library.services.ReservationService;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
// eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjI1OTMxMDk1LCJleHAiOjE2MjYwMTc0OTV9.rFVi0m9RBjXeRrZaMR4b-khnxwDoqlZ5wfJFsC1b2-RLJah6Ldc87Mdo_fkQ-xmPobh7m4fmyvSn2tCCuP5d5g
@RestController
@RequestMapping("/reserve/{book_id}")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AccountService accountService;

    @PutMapping(value = "/extend", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> extend(@PathVariable(value = "book_id") Long bookId, Authentication auth,
                                    @RequestBody String appendDays) {
        String[] s = appendDays      // Ужасная штука, но я не смог взять значение у json
                .replaceAll("\\{", "")
                .replaceAll("\r", "")
                .replaceAll("\n","")
                .replaceAll(" ", "")
                .replaceAll("\"","")
                .replaceAll("}", "")
                .split(":");
        int days = Integer.parseInt(s[1]);
        Account account = accountService.from(auth);
        if (reservationService.updateDateOfEnd(account.getId(),bookId,days)){
            return ResponseEntity.ok("Reservation extended successfully!");
        } throw new IllegalStateException("Reservation can not be extended");
    }

    @PostMapping(value = "/select", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reserve(@PathVariable(value = "book_id") Long bookId, Authentication auth)
    {
        Account account = accountService.from(auth);
        if (reservationService.save(account,bookId)) {
            return ResponseEntity.ok("The book is successfully reserved!");
        } throw new NoSuchElementException("book not found or already reserved");
    }

    @DeleteMapping(value = "/cancel")
    public ResponseEntity<?> cancel(@PathVariable(value = "book_id") Long bookId, Authentication auth){

        Account account = accountService.from(auth);
        if (reservationService.cancel(account,bookId)) return ResponseEntity.ok("Reservation canceled successfully");

        else return ResponseEntity.badRequest().build();
    }

}

