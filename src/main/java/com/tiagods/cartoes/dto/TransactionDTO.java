package com.tiagods.cartoes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.model.OperationType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "transactionId")
public class TransactionDTO {

    @ApiModelProperty(value = "Codigo da Conta")
    @NotNull
    private Long accountId;

    @ApiModelProperty(value = "Codigo da Operação")
    @NotNull
    private Long operationTypeId;

    @ApiModelProperty(value = "Valor da transação")
    @JsonFormat(shape=JsonFormat.Shape.NUMBER_FLOAT)
    @NotNull
    private BigDecimal amount;

    @ApiModelProperty(value = "Data da transação dd/MM/yyyy HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    private Calendar eventDate;

    public TransactionDTO(Long accountId, Long operationTypeId, BigDecimal amount, Calendar eventDate){
        this.accountId=accountId;
        this.operationTypeId=operationTypeId;
        this.amount=amount;
        this.eventDate=eventDate;
    }

}
