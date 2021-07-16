package ru.orion.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.orion.library.models.Account;
import ru.orion.library.services.AccountService;
import ru.orion.library.services.ReservationService;
import ru.orion.library.transfer.AppendDaysDto;
import ru.orion.library.transfer.DurationDto;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reserve/{book_id}")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AccountService accountService;

    @PutMapping(value = "/extend", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> extend(@PathVariable(value = "book_id") Long bookId, Authentication auth,
                                    @RequestBody AppendDaysDto appendDaysDto) {

        Account account = accountService.from(auth);
        int cnt = Integer.parseInt(appendDaysDto.getAppendDays());
        if (reservationService.updateDateOfEnd(account.getId(),bookId,cnt)){
            return ResponseEntity.ok("Reservation extended successfully!");
        } throw new IllegalStateException("Reservation can not be extended");
    }

    @PostMapping(value = "/select", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reserve(@PathVariable(value = "book_id") Long bookId, Authentication auth,
                                     @RequestBody DurationDto durationDto)
    {
        Account account = accountService.from(auth);
        if (reservationService.save(account,bookId,durationDto.getDuration())) {
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

