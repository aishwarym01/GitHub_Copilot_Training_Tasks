package com.app.credit_card_management.controller;

import com.app.credit_card_management.entity.CreditCard;
import com.app.credit_card_management.entity.User;
import com.app.credit_card_management.serviceinterfaces.ICreditCardService;
import com.app.credit_card_management.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CreditCardController {

    @Autowired
    private ICreditCardService creditCardService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<CreditCard>> getCards(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(creditCardService.getCardsByUser(user));
    }

    @PostMapping
    public ResponseEntity<CreditCard> addCard(@RequestBody CreditCard card,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        System.out.println("UserDetails: " + userDetails);
        return ResponseEntity.ok(creditCardService.addCard(card, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCard(@PathVariable Long id) {
        return ResponseEntity.ok(creditCardService.getCardById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateCard(@PathVariable Long id, @RequestBody CreditCard card) {
        return ResponseEntity.ok(creditCardService.updateCard(id, card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        creditCardService.deleteCard(id);
        return ResponseEntity.ok("Card deleted successfully");
    }
}
