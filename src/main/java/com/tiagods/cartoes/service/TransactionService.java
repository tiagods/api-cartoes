package com.tiagods.cartoes.service;

import com.tiagods.cartoes.config.Errors;
import com.tiagods.cartoes.dto.TransactionDTO;
import com.tiagods.cartoes.enums.OperationEnum;
import com.tiagods.cartoes.exception.LimiteNaoDisponivelException;
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

import java.math.BigDecimal;
import java.util.Arrays;
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

    /**
     * Atualizar transação existe
     * @param transactionDto dto com os dados da transação para atualização
     * @param transactionId id no qual sera validado se transaçaão existe
     */

    public void atualizar(TransactionDTO transactionDto, Long transactionId) {
        if (verificarSeExiste(transactionId)) {
            Transaction transaction = validarContaEOperacao(transactionDto);
            transaction.setTransactionId(transactionId);
            transactions.save(transaction);
        } else throw new TransactionException(errors.getTransacaoError().getNaoExiste());
    }

    /**
     * Gerar nova transação com validação de operação
     * @param transactionDto
     * @return retornar transação criada
     */
    public Transaction criar(TransactionDTO transactionDto) {
        Transaction transaction = validarContaEOperacao(transactionDto);
        return validarOperacao(transaction);
    }

    /**
     *
     * @param transactionId
     */
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

    /**
     * A partir da dto verificacr se conta e operação existem
     * @param transactionDto
     * @return retornar transacao a partir da dto
     */
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

    /**
     * Valida operaçao efetuando analise de credito limite
     * @param transaction
     * @return
     */
    private Transaction validarOperacao(Transaction transaction){
        Optional<OperationEnum> operationEnum = OperationEnum.getInstance(
                transaction.getOperationType().getOperationTypeId());
        Account conta = transaction.getAccount();
        BigDecimal limit = conta.getAvailableCreditLimit();
        BigDecimal amount = transaction.getAmount();
        if(operationEnum.isPresent() && !operationEnum.get().equals(OperationEnum.PAGAMENTO)
                    && !verificarSeCreditoDisponivel(limit, amount))
                throw new LimiteNaoDisponivelException(errors.getTransacaoError().getLimiteIndisponivel());
        else if(operationEnum.isEmpty() && !verificarSeCreditoDisponivel(limit, amount))
            throw new LimiteNaoDisponivelException(errors.getTransacaoError().getLimiteIndisponivel());
        BigDecimal calculo = limit.add(transaction.getAmount());
        conta.setAvailableCreditLimit(calculo);
        account.atualizar(conta);
        return transactions.save(transaction);
    }

    /**
     * Verifica saldo disponivel, se a compra for maior que limite returnara true
     * @param limit valor  disponivel
     * @param amount valor solicitado
     * @return true se possue limite
     */
    private boolean verificarSeCreditoDisponivel(BigDecimal limit, BigDecimal amount) {
        return (limit.compareTo(amount.abs())>=0);
    }

}
