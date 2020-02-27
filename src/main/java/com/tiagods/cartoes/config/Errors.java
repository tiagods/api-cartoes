package com.tiagods.cartoes.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(value = "errors")
@Data
public class Errors {

    public final ContaError contaError = new ContaError();
    public final OperacaoError operacaoError = new OperacaoError();
    public final TransacaoError transacaoError = new TransacaoError();

    @Data
    public static class ContaError{
        private String naoExiste;
    }
    @Data
    public static class OperacaoError{
        private String naoExiste;
    }
    @Data
    public static class TransacaoError{
        private String naoExiste;
        private String limiteIndisponivel;
    }
}
