package com.tiagods.cartoes.resource;

import com.tiagods.cartoes.dto.TransactionDTO;
import com.tiagods.cartoes.model.OperationType;
import com.tiagods.cartoes.model.Transaction;
import com.tiagods.cartoes.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/transactions")
public class TransactionResource {

    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@Valid @PathVariable Long id){
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody TransactionDTO transactionDTO){
        service.atualizar(transactionDTO,id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody TransactionDTO transactionDTO){
        Transaction transaction = service.criar(transactionDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(transaction.getTransactionId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
