package com.tiagods.cartoes.service;

import com.tiagods.cartoes.config.Errors;
import com.tiagods.cartoes.exception.AccountException;
import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private Errors errors;

    @Autowired
    private AccountRepository repository;

    /**
     * Atualizar com validação de conta existente
     * @param account Account
     * @param accountId Long numero da conta
     */
    public void atualizar(Account account, Long accountId){
        if(verificarSeExiste(accountId)) {
            account.setAccountId(accountId);
            atualizar(account);
        }
        else throw new AccountException(errors.contaError.getNaoExiste());
    }

    /**
     * Atualizar sem validação
     * @param account Account
     */
    public void atualizar(Account account){repository.save(account);}

    public Account criar(Account account){
        account.setAccountId(null);
        return repository.save(account);
    }
    public void deletar(Long accountId){
        if(verificarSeExiste(accountId)){
            repository.deleteById(accountId);
        }
        else throw new AccountException(errors.contaError.getNaoExiste());
    }
    private Optional<Account> buscar(Long accountId){
        return repository.findById(accountId);
    }

    public Account buscarPorId(Long accountId){

        Optional<Account> account = buscar(accountId);
        if(account.isPresent()) return account.get();
        else throw new AccountException(errors.contaError.getNaoExiste());

    }
    private boolean verificarSeExiste(Long accountId) {
        return repository.existsById(accountId);
    }

    public List<Account> listar() {
        return repository.findAll();
    }
}
