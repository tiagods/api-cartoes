package com.tiagods.cartoes.service;

import com.tiagods.cartoes.exception.AccountException;
import com.tiagods.cartoes.exception.OperationTypeException;
import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.model.OperationType;
import com.tiagods.cartoes.repository.OperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationTypeService  {

    @Autowired
    private OperationTypeRepository repository;

    public void atualizar(OperationType operationType, Long operationId){
        if(verificarSeExiste(operationId)) {
            operationType.setOperationTypeId(operationId);
            repository.save(operationType);
        }
        else throw new OperationTypeException("Não existe com o id informado");
    }
    public OperationType criar(OperationType operationType){
        if(verificarSeExiste(operationType.getOperationTypeId()))
            throw new OperationTypeException("Ja existe um registro com id informado");
        else return repository.save(operationType);
    }
    public void deletar(Long operationTypeId){
        if(verificarSeExiste(operationTypeId)){
            repository.deleteById(operationTypeId);
        }
        else throw new OperationTypeException("Não existe com o id informado");
    }
    public Optional<OperationType> buscar(Long operationTypeId){
        return repository.findById(operationTypeId);
    }

    public List<OperationType> listar() {
        return repository.findAll();
    }

    /**
     *
     * @param operationTypeId parametro usado para validar registro na base
     * @return
     */
    private boolean verificarSeExiste(Long operationTypeId) {
        return repository.existsById(operationTypeId);
    }

    public OperationType buscarPorId(Long operationTypeId) {
        Optional<OperationType> account = buscar(operationTypeId);
        if(account.isPresent()) return  account.get();
        else throw new OperationTypeException("Não existe com  o id informado");
    }
}
