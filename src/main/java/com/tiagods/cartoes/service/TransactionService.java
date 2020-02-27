package com.tiagods.cartoes.service;

import com.tiagods.cartoes.config.Errors;
import com.tiagods.cartoes.dto.TransactionDTO;
import com.tiagods.cartoes.exception.TransactionException;
import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.model.OperationType;
import com.tiagods.cartoes.model.Transaction;
import com.tiagods.cartoes.repository.AccountRepository;
import com.tiagods.cartoes.repository.OperationTypeRepository;
import com.tiagods.cartoes.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private Errors errors;

    @Autowired
    private TransactionRepository transactions;

    @Autowired
    private OperationTypeService operation;

    @Autowired
    private AccountService account;

    public void atualizar(TransactionDTO transactionDto, Long transactionId) {
        if (verificarSeExiste(transactionId)) {
            Transaction transaction = validarContaEOperacao(transactionDto);
            transaction.setTransactionId(transactionId);
            transactions.save(transaction);
        } else throw new TransactionException(errors.getTransacaoError().getNaoExiste());
    }

    public Transaction criar(TransactionDTO transactionDto) {
        Transaction transaction = validarContaEOperacao(transactionDto);
        return transactions.save(transaction);
    }

    public void deletar(Long transactionId) {
        if (verificarSeExiste(transactionId)) {
            transactions.deleteById(transactionId);
        } else throw new TransactionException(errors.getTransacaoError().getNaoExiste());
    }

    public Optional<Transaction> buscar(Long transactionId) {
        return transactions.findById(transactionId);
    }

    public Transaction buscarPorId(Long transaction){
        Optional<Transaction> account = buscar(transaction);
        if(account.isPresent()) return account.get();
        else throw new TransactionException(errors.getTransacaoError().getNaoExiste());
    }
    private boolean verificarSeExiste(Long transactionId) {
        return transactions.existsById(transactionId);
    }

    private Transaction validarContaEOperacao(TransactionDTO transactionDto) {
        Transaction transaction = new Transaction(transactionDto);
        Account account = this.account.buscarPorId(transactionDto.getAccountId());
        OperationType operationType = operation.buscarPorId(transactionDto.getOperationTypeId());
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        return transaction;
    }

    public List<Transaction> listar() {
        return transactions.findAll();
    }
}
