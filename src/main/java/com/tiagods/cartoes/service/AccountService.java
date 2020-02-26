package com.tiagods.cartoes.service;

import com.tiagods.cartoes.exception.AccountException;
import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public void atualizar(Account account, Long accountId){
        if(verificarSeExiste(accountId)) {
            account.setAccountId(accountId);
            repository.save(account);
        }
        else throw new AccountException("Não existe com o id informado");
    }
    public Account criar(Account account){
        if(verificarSeExiste(account.getAccountId()))
            throw new AccountException("Ja existe um registro com id informado");
        else return repository.save(account);
    }
    public void deletar(Long accountId){
        if(verificarSeExiste(accountId)){
            repository.deleteById(accountId);
        }
        else throw new AccountException("Não existe com o id informado");
    }
    private Optional<Account> buscar(Long accountId){
        return repository.findById(accountId);
    }

    public Account buscarPorId(Long accountId){

        Optional<Account> account = buscar(accountId);
        if(account.isPresent()) return account.get();
        else throw new AccountException("Não existe com  o id informado");

    }
    private boolean verificarSeExiste(Long accountId) {
        return repository.existsById(accountId);
    }

    public List<Account> listar() {
        return repository.findAll();
    }
}
