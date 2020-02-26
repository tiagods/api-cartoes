package com.tiagods.cartoes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "transactionId")
public class Transaction {
    @ApiModelProperty(value = "Codigo da transação")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Transaction_ID", updatable = false, nullable = false)
    private Long transactionId;

    @ApiModelProperty(value = "Conta")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Account_ID")
    private Account account;

    @ApiModelProperty(value = "Operação")
    @ManyToOne
    @JoinColumn(name = "OperationType_ID")
    private OperationType operationType;

    @ApiModelProperty(value = "Valor da transação")
    @Column(name = "Amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "Data da transação dd/MM/yyyy HH:mm:ss")
    @Column(name = "EventDate")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Calendar eventDate;

    public Transaction(Account account, OperationType operationType, BigDecimal amount, Calendar eventDate){
        this.account=account;
        this.operationType=operationType;
        this.amount=amount;
        this.eventDate=eventDate;
    }
}
