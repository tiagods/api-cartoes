package com.tiagods.cartoes.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionResource {

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.noContent().build();
    }
}
