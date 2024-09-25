package com.eazybytes.cards.controllers;


import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.record.CardsInfoDto;
import com.eazybytes.cards.services.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CardsController {

    private final ICardsService cardsService;

    @Autowired
    private  CardsInfoDto cardsInfoDto;

    @PostMapping("/create")
    public ResponseEntity<CardsDto> createCard(@RequestParam  String mobileNumber) {
        CardsDto card = cardsService.createCard(mobileNumber);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<CardsDto> updateCard(@RequestParam  String mobileNumber, @RequestBody CardsDto card) {
        CardsDto cardsDto = cardsService.updateCard(card, mobileNumber);
        return new ResponseEntity<>(cardsDto, HttpStatus.OK);
    }


    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> getCard(@RequestParam  String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return new ResponseEntity<>(cardsDto, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public Boolean deleteCard(@RequestParam  String mobileNumber) {
        return cardsService.deleteCard(mobileNumber);
    }

    @GetMapping("/build-info")
    public ResponseEntity<CardsInfoDto> getCardInfo() {
        return new ResponseEntity<>(cardsInfoDto, HttpStatus.OK);
    }

}
