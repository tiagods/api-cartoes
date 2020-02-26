package com.tiagods.cartoes.service;

import com.tiagods.cartoes.exception.AccountException;
import com.tiagods.cartoes.exception.TransactionException;
import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.model.Transaction;
import com.tiagods.cartoes.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public void atualizar(Transaction transaction, Long transactionId) {
        if (verificarSeExiste(transactionId)) {
            transaction.setTransactionId(transactionId);
            repository.save(transaction);
        } else throw new TransactionException("Não existe com o id informado");
    }

    public void criar(Transaction transaction) {
        if (verificarSeExiste(transaction.getTransactionId()))
            throw new TransactionException("Ja existe um registro com id informado");
        else if (validarContaEOperacao(transaction)) repository.save(transaction);
    }

    public void deletar(Long transactionId) {
        if (verificarSeExiste(transactionId)) {
            repository.deleteById(transactionId);
        } else throw new TransactionException("Não existe com o id informado");
    }

    public Optional<Transaction> buscar(Long transactionId) {
        return repository.findById(transactionId);
    }

    private boolean verificarSeExiste(Long transactionId) {
        return repository.existsById(transactionId);
    }

    private boolean validarContaEOperacao(Transaction transaction) {
        return true;
    }
}
