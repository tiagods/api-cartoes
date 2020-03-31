package com.tiagods.cartoes;

import com.tiagods.cartoes.config.H2ConfigTest;
import com.tiagods.cartoes.exception.AccountException;
import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.repository.AccountRepository;
import com.tiagods.cartoes.service.AccountService;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CartoesApplication.class, H2ConfigTest.class})
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountsFakeDBTestServices {

    @Autowired AccountService accountService;

    @BeforeAll
    void initTest(){
        Assert.assertNotNull(accountService);
        List.of(
                    new Account(null, 123456L, new BigDecimal("1000"),null),
                    new Account(null, 1234567L, new BigDecimal("2000"),null),
                    new Account(null, 123456789L, new BigDecimal("100"),null)
                )
                .forEach(account -> accountService.criar(account));
    }

    @Test
    @Order(1)
    public void test_buscar(){
        Assert.assertFalse(accountService.listar().isEmpty());
    }

    @Test(expected = AccountException.class)
    @Order(2)
    public void test_buscar_inexistente(){
        accountService.buscarPorId(123L);
    }

    @Test
    @Order(3)
    public void test_listar(){
        Assert.assertFalse(accountService.listar().isEmpty());
    }


    @Test
    @Order(4)
    public void test_criar_conta(){
        Account account = new Account(null,12345L,new BigDecimal("5000.00"),null);
        account = accountService.criar(account);
        Assert.assertEquals(1L,account.getAccountId().longValue());
    }

    @Test(expected = ConstraintViolationException.class)
    @Order(5)
    public void test_criar_conta_document_null() {
        Account account = new Account(null,null,new BigDecimal("5000.00"),null);
        accountService.criar(account);
    }
    @Test
    @Order(6)
    public void test_atualizar_conta() {
        Account account = new Account(null, 12345678L, new BigDecimal("500"), null);
        account = accountService.criar(account);
        BigDecimal limiteAnterior = account.getAvailableCreditLimit();
        BigDecimal novoLimite = new BigDecimal("300.00");
        account.setAvailableCreditLimit(novoLimite);
        accountService.atualizar(account, account.getAccountId());
        account = accountService.buscarPorId(account.getAccountId());
        Assert.assertNotEquals(account.getAvailableCreditLimit(), limiteAnterior);
        Assert.assertEquals(novoLimite, account.getAvailableCreditLimit());
    }
    @Test(expected = AccountException.class)
    @Order(6)
    public void test_atualizar_conta_nao_existente() {
        Account account = new Account(9999L, 12345678L, new BigDecimal("500"),null);
        accountService.atualizar(account, account.getAccountId());
    }
    @Test(expected = AccountException.class)
    @Order(7)
    public void test_deletar_conta() {
        accountService.deletar(1L);
        accountService.buscarPorId(1L);
    }
    @Test(expected = AccountException.class)
    @Order(7)
    public void test_deletar_conta_inexistente() {
        accountService.deletar(500L);
    }
}
